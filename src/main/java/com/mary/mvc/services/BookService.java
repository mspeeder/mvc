package com.mary.mvc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.mary.mvc.models.Book;
import com.mary.mvc.repositories.BookRepository;

@Service
public class BookService {
	// create repository instance
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	// returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    
    //
    public List<Book> BooksWithTitle(String title) {
        return bookRepository.findAllByTitleContaining(title);
    }
    
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
   // assignment using BooksApi controller
   // to update a book, use the setter methods form the domain model and then call save method in CrudRepository
	public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setTitle(title);
			book.setDescription(desc);
		    book.setLanguage(lang);
		    book.setNumberOfPages(numOfPages);
		    // save() is used for both creating and updating
			bookRepository.save(book);
	        return optionalBook.get();
	    } else {
	    	return null;
	    }
	}
	
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

	
	public void updateBook(@Valid Book book) {
		bookRepository.save(book);
	}
}
