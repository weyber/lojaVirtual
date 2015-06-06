package bean;

import java.io.Serializable;
import java.util.Collection;

import model.Item;
import model.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import utilFaces.UtilFaces;
import dao.IDao;

@Controller("orderBean")
@Scope("session")
public class OrderBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ItemBean itemBean;

    @Autowired
    private IDao dao;

    private Orders orders;
    
    public OrderBean() {
        orders = new Orders();
    }
    
    public String list() {
        orders.setItems(dao.select(itemBean.getUser()));

        if (!orders.getItems().isEmpty()) {
            return "report?faces-redirect=true";
        } else {
            UtilFaces.save("User not buy any book !");
            return null;
        }
    }

    public double getTotalValue() {
        double totalValue = 0;

        for (Item oneItem : orders.getItems()) {
            totalValue = totalValue + oneItem.getValue();
        }
        return totalValue;
    }

    public double subValue(int id) {
        double subvalue = 0;

        for (Item oneItem : orders.getItems()) {
            if (oneItem.getOrders().getId() == id) {
                subvalue = subvalue + oneItem.getValue();
            }
        }
        return subvalue;
    }

    /* Gets and Sets */
    public Collection<Item> getRequestItems() {
        return orders.getItems();
    }
}