package com.example.subscriptionservice.bookproxy;

public class Book {

	private Long id;
	private String bookId;
	private String name;
	private String author;
	private Integer avaliableCopies;
	private Integer totalCopies;
	
	public Book(Long id, String bookId, String name, String author, Integer avaliableCopies, Integer totalCopies) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.avaliableCopies = avaliableCopies;
		this.totalCopies = totalCopies;
	}
	
	public Book() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getAvaliableCopies() {
		return avaliableCopies;
	}

	public void setAvaliableCopies(Integer avaliableCopies) {
		this.avaliableCopies = avaliableCopies;
	}

	public Integer getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(Integer totalCopies) {
		this.totalCopies = totalCopies;
	}


	 @Override
	public String toString() {
		return id + ", "+ bookId + ", " + name + ", " + author + ", " + avaliableCopies + ", " + totalCopies + ";";
	}
}
