package com.example.subscriptionservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SubscriptionServiceApplicationTests {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Test
	void loadDataTest() {
		assertEquals(1, subscriptionRepository.findAll().size());
	}

}
