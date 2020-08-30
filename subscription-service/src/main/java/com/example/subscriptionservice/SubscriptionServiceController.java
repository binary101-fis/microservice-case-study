package com.example.subscriptionservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.subscriptionservice.bookproxy.Book;
import com.example.subscriptionservice.bookproxy.BookServiceProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class SubscriptionServiceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	BookServiceProxy bookServiceProxy;

	@GetMapping("/subscriptions")
	public List<Subscription> retrieveSubscriptions() {
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		return subscriptions;

	}

	@PostMapping("/subscription")
	@HystrixCommand(fallbackMethod = "fallbackForCreateSubscription")
	public ResponseEntity<ResponseMessage> createSubscription(@RequestBody Subscription subscription) {

		ResponseEntity<Book[]> response = new RestTemplate().getForEntity("http://localhost:8001/books", Book[].class);

		Book[] books = response.getBody();
		Book book = null;
		for (Book obj : books) {
			if (obj.getBookId().equals(subscription.getBookId())) {
				book = obj;
				break;
			}
		}
		return save(subscription, book);

	}

	@PostMapping("/subscription-feign")
	@HystrixCommand(fallbackMethod = "fallbackForCreateSubscription")
	public ResponseEntity<ResponseMessage> createSubscriptionUsingFeign(@RequestBody Subscription subscription) {

		Book book = bookServiceProxy.retrieveBook(subscription.getBookId());
		return save(subscription, book);

	}

	private ResponseEntity<ResponseMessage> save(Subscription subscription, Book book) throws RuntimeException {
		if (book != null) {
			logger.info("Book retrieved successfully: " + book.toString());

			if (book.getAvaliableCopies() > 0) {

				book.setAvaliableCopies(book.getAvaliableCopies() - 1);
				ResponseEntity<ResponseMessage> response = bookServiceProxy.updateBook(book);

				if (response.getStatusCodeValue() == 200) {
					logger.info("Request Subscription create : " + subscription.toString());
					Subscription subscriptionNew = subscriptionRepository.saveAndFlush(subscription);
					logger.info("Subscription succesfully created : " + subscriptionNew.toString());
				} else {
					throw new RuntimeException("Exception occurred while Book update");
				}

				return ResponseEntity.status(201).body(new ResponseMessage("201", "Subscription created successfully"));
			} else {
				return ResponseEntity.unprocessableEntity().body(new ResponseMessage("422",
						"Book with bookId: " + subscription.getBookId() + " does not have available copies"));
			}

		} else {
			return ResponseEntity.unprocessableEntity().body(
					new ResponseMessage("422", "Book with bookId: " + subscription.getBookId() + " does not exist"));
		}
	}

	public ResponseEntity<ResponseMessage> fallbackForCreateSubscription(@RequestBody Subscription subscription,
			Throwable e) {

		logger.error(e.getMessage());
		e.printStackTrace();
		return ResponseEntity.status(500).body(new ResponseMessage("500", "Internal Server error occurred."));
	}

}
