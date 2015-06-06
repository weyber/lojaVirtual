package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity = 1;
    
    @ManyToOne
    private Book book;
    
    @ManyToOne
    private Orders orders;
    
    /* Constructor */
    public Item() {
    }

    public Item(Book book) {
        this.book = book;
    }
    
    /* Gets and Sets */    
    public double getValue() {
        return book.getValue() * quantity;
    }
    
    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
    
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

     public Long getId() {
        return id;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
