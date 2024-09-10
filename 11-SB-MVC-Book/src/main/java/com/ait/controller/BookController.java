package com.ait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ait.entitiy.Book;
import com.ait.service.BookService;

@Controller
public class BookController {

	// Field-Injuction
	@Autowired
	private BookService service;

	// Adding index page to send empty object for form data binding
	@GetMapping("/index")
	public ModelAndView index() {
		// Sending empty obj for form binding
		ModelAndView mav = new ModelAndView();
		mav.addObject("book", new Book());

		mav.setViewName("index");
		return mav;
	}

	// Performing CRUD Operation using Spring Web MVC

	// Saving (Create ) Book Record.
	@PostMapping("/book")
	public ModelAndView saveBook(Book book) {
		ModelAndView mav = new ModelAndView();
		boolean status = service.saveBook(book);
		if (status) {
			mav.addObject("succMsg", "Book Saved");
		} else {
			mav.addObject("errMsg", "Failed to save");
		}

		mav.setViewName("index");
		return mav;
	}

	// Edit (Update) Book Record
	@GetMapping("/edit")
	public ModelAndView editBook(@RequestParam("bookId") Integer bookId) {
		Book bookObj = service.getBookById(bookId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("book", bookObj);
		mav.setViewName("index");
		return mav;
	}

	// Fetching (Reading ) Saved Book Records
	@GetMapping("/books")
	public ModelAndView getBooks() {
		ModelAndView mav = new ModelAndView();
		List<Book> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("booksView");
		return mav;
	}

	// Delete book record
	@GetMapping("/delete")
	public ModelAndView deleteBook(@RequestParam("bookId") Integer bookId) {
		service.deleteBook(bookId);
		ModelAndView mav = new ModelAndView();
		List<Book> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("booksView");
		return mav;
	}

}
