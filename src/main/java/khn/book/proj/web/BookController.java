package khn.book.proj.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import khn.book.proj.bean.CustomPropertyEditorRegistrar;
import khn.book.proj.model.Book;
import khn.book.proj.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springinpractice.web.ResourceNotFoundException;

/**
 * Spring Web MVC controller exposing a RESTful interface for book-related operations.
 * 
 * @author Kathie
 */
@Controller
@RequestMapping(value = "/books")
public class BookController {
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Inject private BookService bookService;
	
	@Value("#{viewNames.bookList}")
	private String bookListViewName;
	
	@Value("#{viewNames.bookForm}")
	private String bookFormViewName;
	
	@Value("#{viewNames.createBookSuccess}")
	private String createBookSuccessViewName;
	
	@Value("#{viewNames.updateBookSuccess}")
	private String updateBookSuccessViewName;
	
	@Value("#{viewNames.deleteBookSuccess}")
	private String deleteBookSuccessViewName;
	
	@Value("#{viewNames.bookSerp}")
	private String bookSerpViewName;
	
	@InitBinder
	/*public void initBinder(WebDataBinder binder) {*/
		/*binder.findCustomEditor(requiredType, propertyPath)*/
	public void initBinder(WebDataBinder binder) {
		/*binder.registerCustomEditor(Date.class, new CustomDateEditor(dateWithFormatting, true, 10));*/
		binder.setAllowedFields(new String[] {
			/*"name", "isbn", "author"*/
			"name", "isbn", "author", "publishDate"
		});
	}
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createBookForm(HttpServletRequest req, Model model) {
		prepareNewBookForm(req);
		model.addAttribute(new Book());
		return bookFormViewName;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createBook(
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute @Valid Book book,
			BindingResult result) {
		
		if (!result.hasErrors()) {
			bookService.createBook(book);
			
			// Correct RESTful semantics involves setting the status and location, but these will be overridden if the
			// createBookSuccessViewName issues a redirect.
			res.setStatus(HttpServletResponse.SC_CREATED);
			String location = req.getRequestURL() + "/" + book.getId();
			log.debug("Setting Location={}", location);
			res.setHeader("Location", location);
			
			return createBookSuccessViewName;
		} else {
			prepareNewBookForm(req);
			result.reject("global.error");
			return bookFormViewName;
		}
	}

	/**
	 * Places a list containing all books on the passed model, and returns the logical view name for displaying the
	 * book list.
	 * 
	 * @param model
	 *            model
	 * @return book list view name
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getBooks(Model model) {
		model.addAttribute(bookService.getBooks());
		return bookListViewName;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchByEmail(@RequestParam("name") String name, Model model) {
		model.addAttribute(bookService.getBooksByKeyword(name));
		return bookSerpViewName;
	}

	/**
	 * Places the requested book on the passed model if it exists, and returns the logical view name for displaying
	 * the book. Throws a {@link ResourceNotFoundException} and returns an HTTP 404 (NOT_FOUND) to the client if the
	 * request book doesn't exist.
	 * 
	 * @param id
	 *            book ID
	 * @param model
	 *            model
	 * @return book view name
	 * @throws ResourceNotFoundException
	 *             if no such book exists
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getBook(
			HttpServletRequest req,
			@PathVariable("id") long id,
			Model model) {
		
		Book book = bookService.getBook(id);
		if (book != null) {
			prepareExistingBookForm(req, id);
			model.addAttribute(bookService.getBook(id));
			return bookFormViewName;
		} else {
			throw new ResourceNotFoundException("No such book: " + id);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateBook(
			HttpServletRequest req,
			@PathVariable("id") Long id,
			@ModelAttribute @Valid Book book,
			BindingResult result) {
		
		book.setId(id);
		
		if (!result.hasErrors()) {
			bookService.updateBook(book);
			return updateBookSuccessViewName;
		} else {
			prepareExistingBookForm(req, id);
			result.reject("global.error");
			return bookFormViewName;
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteBook(@PathVariable("id") long id) {
		bookService.deleteBook(id);
		return deleteBookSuccessViewName;
	}
	
	
	// =================================================================================================================
	// Helper methods
	// =================================================================================================================
	
	private void prepareNewBookForm(HttpServletRequest req) {
		setActionAndMethod(req, "/books.html", "POST");
	}

	private void prepareExistingBookForm(HttpServletRequest req, long id) {
		setActionAndMethod(req, "/books/" + id + ".html", "PUT");
	}
	
	private void setActionAndMethod(HttpServletRequest req, String action, String method) {
		req.setAttribute("action", action);
		req.setAttribute("method", method);
	}
}
