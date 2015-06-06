package dao;

import java.util.List;

import model.Book;
import model.Item;


public interface IDao {

    public void insert(Object object);

    public List<Book> select();
    
    public List<Book> selectBooks(String string);
    
    public List<Item> select(String login);
}
