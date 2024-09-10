package com.ait.service;

import java.util.List;

import com.ait.entitiy.Book;

public interface BookService {
	// To get All Records
	public List<Book> getAllBooks();

	// To Save Record
	public boolean saveBook(Book book);

	// To Delete Record
	public void deleteBook(Integer bookId);

	// To fetch Record By ID
	public Book getBookById(Integer bookId);

}
