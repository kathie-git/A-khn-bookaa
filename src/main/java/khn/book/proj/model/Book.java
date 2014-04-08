
/**
 * @author Kathie Nguyen
 */

package khn.book.proj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.springinpractice.util.StringUtils;

import java.util.Date;

/**
 * @author Kathie
 *
 */
@Entity
@Table(name = "book")
@NamedQuery (
	name = "findBooksByKeyword",
	query = "from Book where name like :name"
	)
public class Book  {
	private Long id;
	private String isbn;
    private String name;
    private String author;
    /*private Date publishDate;*/
    
    @Id
    @Column
    @GeneratedValue (strategy = GenerationType.AUTO)
    public Long getId() {return id;}
    public void setId(Long id) {
    	this.id = id;
    }

    @NotNull
    @Length(min = 1, max = 20)
    @Column(name = "isbn")
	public String getIsbn() {
		return isbn;
	}
    
	public void setIsbn(String isbn) {
		this.isbn = StringUtils.cleanup(isbn);
	}
	
	@NotNull
	@Length(min = 1, max = 40)
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = StringUtils.cleanup(name);
	}
	
	@NotNull
	@Length(min = 1, max= 40)
	@Column(name = "author")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/*@NotNull*/
	/*need JodaTime*/
	/*@DateTimeFormat(pattern="dd/mm/yyyy")	*/
	/*public Date getPublishDate() {
		return publishDate;
	}*/
	/*@Column
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}*/
}

