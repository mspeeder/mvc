package com.mary.mvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mary.mvc.models.Book;
import com.mary.mvc.services.BookService;

@RestController
public class BooksApi {
	private final BookService bookService;

	public BooksApi(BookService bookService) {
		this.bookService = bookService;
	}
	
	 @RequestMapping("/api/books")
	    public List<Book> index() {
	        return bookService.allBooks();
	    }
	 
	 @RequestMapping("/api/{title}")
	    public List<Book> title(@PathVariable("title") String title) {
		 List<Book> book = bookService.BooksWithTitle(title);
	        return book ;
	    }
	 
	 //create a book
	 @RequestMapping(value="/api/books", method=RequestMethod.POST)
	    public Book create(@RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
	        Book book = new Book(title, desc, lang, numOfPages);
	        return bookService.createBook(book);
	    }
	
	 @RequestMapping("/api/books/{id}")
	 public Book show(@PathVariable("id") Long id) {
		 Book book = bookService.findBook(id);
		 return book;
	 }
	 
	 //update a book - assignment
	 @RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
	 public Book update(@PathVariable("id") Long id, @RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
	     Book book = bookService.updateBook(id, title, desc, lang, numOfPages);
	     return book;
	 }
	 
	 //destroy a book - assignment
	 @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
	    public void destroy(@PathVariable("id") Long id) {
	        bookService.deleteBook(id);
	    }

}
