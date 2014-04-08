/**
 * @author Kathie Nguyen
 */


/**
 * @author Kathie
 *
 */

package khn.book.proj.dao;

import java.util.List;

import khn.book.proj.model.Book;
import khn.spring.dao.Dao;


public interface BookDao extends Dao<Book> {
	List<Book> findByName(String name);
}
