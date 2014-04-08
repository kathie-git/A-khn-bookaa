
/**
 * @author Kathie Nguyen
 */
package khn.book.proj.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.inject.Inject;

import khn.book.proj.dao.BookDao;
import khn.book.proj.model.Book;
import khn.book.proj.service.BookService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Book service bean
 * @author Kathie
 *
 */
@Service
@Transactional
public class BookServiceImpl implements BookService{
	@Inject private BookDao bookDao;
	@Override
	public void createBook(Book book) {
		notNull(book, "book cannot be null");
		bookDao.create(book);
	}

	@Override
	public List<Book> getBooks() {
		return bookDao.getAll();
	}

	@Override
	public Book getBook(Long id) {
		notNull(id, "id cannot be null");
		return bookDao.get(id);
		
	}

	@Override
	public List<Book> getBooksByKeyword(String name) {
		notNull(name, "name cannot be null");
		return bookDao.findByName(name);
	}
	@Override
	public void updateBook(Book book) {
		notNull(book, "bookcannot be null");
		bookDao.update(book);
	}

	@Override
	public void deleteBook(Long id) {
		notNull(id, "idcannot be null");
		bookDao.deleteById(id);
	}

}

