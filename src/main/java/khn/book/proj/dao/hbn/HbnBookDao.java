package khn.book.proj.dao.hbn;

import java.util.List;

import khn.book.proj.dao.BookDao;
import khn.book.proj.model.Book;

import org.springframework.stereotype.Repository;

import static org.springframework.util.Assert.notNull;
import khn.spring.dao.hibernate.AbstractHbnDao;
    

/**
 * @author Kathie
 *
 */
@Repository
public class HbnBookDao extends AbstractHbnDao<Book> implements BookDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findByName(String name) {
		notNull(name, "name cannot be null");
		return getSession()
				.getNamedQuery("findBooksByKeyword")
				.setString("name", "%" + name + "%")
				.list();
	}

}

