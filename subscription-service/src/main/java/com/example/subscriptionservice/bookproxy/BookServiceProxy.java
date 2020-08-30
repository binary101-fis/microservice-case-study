package com.example.subscriptionservice.bookproxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.subscriptionservice.ResponseMessage;

//@FeignClient(name="book-service",url="localhost:8001") //using hardcoded url to connect to particular book service
@FeignClient(name = "book-service") // removed hardcoded url to use ribbon
@RibbonClient(name = "book-service")
public interface BookServiceProxy {

	@GetMapping("/books")
	public List<Book> retrieveBooks();

	@GetMapping("/books/{bookId}")
	public Book retrieveBook(@PathVariable("bookId") String bookId);

	@PutMapping("/books")
	public ResponseEntity<ResponseMessage> updateBook(@RequestBody Book book);
}
