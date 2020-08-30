package com.example.bookservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookApplicationTests {

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	void loadDataTest() {
		assertEquals(3, bookRepository.findAll().size());
	}

}
