package com.example.subscriptionservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "subscriber_name")
	private String subscriberName;
	@Column(name = "date_subscribed")
	private String dateSubscribed;
	@Column(name = "date_returned")
	private String dateReturned;
	@Column(name = "book_id")
	private String bookId;

	public String getSubscriberName() {
		return subscriberName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getDateSubscribed() {
		return dateSubscribed;
	}

	public void setDateSubscribed(String dateSubscribed) {
		this.dateSubscribed = dateSubscribed;
	}

	public String getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(String dateReturned) {
		this.dateReturned = dateReturned;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Override
	public String toString() {
		return id + ", " + subscriberName + ", " + dateSubscribed + ", " + dateReturned + ", " + bookId;
	}
}
