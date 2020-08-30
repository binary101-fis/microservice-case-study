package com.example.bookservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/books")
	public List<Book> retrieveBooks() {

		List<Book> bookList = bookRepository.findAll();

		return bookList;

	}
	
	@GetMapping("/books/{bookId}")
	public Book retrieveBook(@PathVariable("bookId") String bookId) {

		logger.info("Find Book by bookId : " + bookId);
		List<Book> bookList = bookRepository.findByBookId(bookId);

		if(bookList != null && bookList.size() == 1) {
			logger.info("Book succesfully retrieved : " + bookList.get(0).toString());
			return bookList.get(0);
		}
		logger.info("Book does not exist");
		return null;
	}
	
	@PutMapping("/books")
	public ResponseEntity<ResponseMessage> updateBook(@RequestBody Book book) {

		logger.info("Request Book update : " + book.toString());
		Book updateBook = bookRepository.saveAndFlush(book);
		logger.info("Book succesfully updated : " + updateBook.toString());
		return ResponseEntity.ok().body(new ResponseMessage("200", "Book succesfully updated"));
		
	}
}
