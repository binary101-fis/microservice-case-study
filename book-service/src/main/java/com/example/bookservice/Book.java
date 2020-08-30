package com.example.bookservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "book_id")
	private String bookId;
	private String name;
	private String author;
	@Column(name = "available_copies")
	private Integer avaliableCopies;
	@Column(name = "total_copies")
	private Integer totalCopies;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + ", " + bookId + ", " + name + ", " + author + ", " + avaliableCopies + ", " + totalCopies + ";";
	}
}
