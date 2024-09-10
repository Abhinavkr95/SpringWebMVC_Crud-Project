package com.ait.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.entitiy.Book;
import com.ait.repo.BookRepositiry;

@Service
public class BookServiceImpl implements BookService {
	// Field Injunction
	@Autowired
	private BookRepositiry bookRepo;

	// To check into Database record is Active or Not
	@Override
	public List<Book> getAllBooks() {
		// get all record by findAll()Method
		// return bookRepo.findAll();
		// Only Active Record Show on Page
		return bookRepo.findByActiveSW("Y");
	}

	// To Save All Record
	@Override
	public boolean saveBook(Book book) {
		book.setActiveSW("Y");
		Book saveBook = bookRepo.save(book);
		// return saveBook.getBookId()!=null;
		if (saveBook.getBookId() != null) {
			return true;
		}
		return false;
	}

	// To delete Record
	@Override
	public void deleteBook(Integer bookId) {
		// Hard Delete
		// bookRepo.deleteById(bookId);

		// Soft-Delete
		Optional<Book> findByID = bookRepo.findById(bookId);
		Book book = findByID.get();
		book.setActiveSW("N");
		bookRepo.save(book);
	}

	// To check record
	@Override
	public Book getBookById(Integer bookId) {
		Optional<Book> findByID = bookRepo.findById(bookId);
		if (findByID.isPresent()) {
			return findByID.get();
		}
		return null;
	}

}
