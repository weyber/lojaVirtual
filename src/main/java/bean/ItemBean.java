package bean;

import java.io.Serializable;
import java.util.Collection;

import model.Book;
import model.Item;
import model.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import utilFaces.UtilFaces;
import dao.IDao;

@Controller
@Scope("session")
public class ItemBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Orders orders;

    private String user;

    @Autowired
    private IDao dao;

    /* Constructor */
    public ItemBean() {
        orders = new Orders();
        user = new String();
    }

    public void createItem(Book book) {
        Item itemAux = new Item(book);

        if(search(book, itemAux)){
            UtilFaces.save("Book added to cart !");
            getItems().add(itemAux);
        }
        else {
            UtilFaces.save("Book is already in the cart !");
        }
    }

    public boolean search(Book book, Item itemAux) {
        boolean bool = true;
        for (Item item : getItems()) {
            if(book.getId().equals(item.getBook().getId() )) {
                bool = false;
                break;
            }
        }
        return bool;
    }

    public void logout() {
       user = "";
    }
    
    /* Gets and Sets */
    public double getTotalValue() {
        double totalValue = 0;

        for (Item umItem : getItems()) {
            totalValue = totalValue + umItem.getValue();
        }
        return totalValue;
    }
    
    public Collection<Item> getItems() {
        return orders.getItems();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
