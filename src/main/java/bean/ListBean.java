package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dao.IDao;

@Controller("listBean")
@Scope("session")
public class ListBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Book book;

    private List<Book> books;

    private String search;

    private String auxSearch;

    @Autowired
    IDao dao;

    @PostConstruct
    public void init() {
        books = dao.select();
    }

    /** Constructor */
    public ListBean() {
        books = new ArrayList<Book>();
    }

    public void read() {
        if(!search.equals(auxSearch)) {
            books = dao.selectBooks(search);
            auxSearch = search;
        }
    }

    /* Gets and Sets */
    public List<Book> getBooks() {
        return books;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
