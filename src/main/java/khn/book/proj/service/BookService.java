
/**
 * @author Kathie Nguyen
 */
package khn.book.proj.service;

import java.util.List;

import khn.book.proj.model.Book;


/**
 * @author Kathie
 *
 */
public interface BookService {
	
	/**
	 * @param book
	 */
	void createBook(Book book);
	
	/**
	 * @return a list of all Books
	 */
	List<Book> getBooks();
	
	/**
	 * @param name
	 * @return a book or list of books matching the keyword of a book/books
	 */
	List<Book> getBooksByKeyword(String name);
	
	/**
	 * @param id
	 * @return a book for the given id
	 */
	Book getBook(Long id);
	
	/**
	 * @param book
	 */
	void updateBook(Book book);
	
	/**
	 * @param id
	 */
	void deleteBook(Long id);

	

}

