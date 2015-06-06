package bean;

import java.util.Collection;

import model.Item;
import model.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import utilFaces.UtilFaces;
import dao.IDao;
import dao.IDaoCustomer;

@Controller
@Scope("request")
public class FinalizeBean {

    private Orders orders;

    @Autowired
    IDaoCustomer daoCustomer;
    
    @Autowired 
    IDao dao;
    
    @Autowired
    ItemBean itemBean;
    
    /* Constructor*/
    public FinalizeBean() {
        orders = new Orders();
    }

    public String end() {
        String user = itemBean.getUser();
        Collection<Item> items = itemBean.getItems();
        
        if(!user.isEmpty()) {

            for (Item item : items) {
                item.setOrders(orders);
            }

            orders.setCustomer(daoCustomer.select(user));
            orders.setItems(items);
            dao.insert(orders);
            items.clear();
            UtilFaces.save("Completed purchase !");
            return "";
        }
        return "login?faces-redirect=true";
    }

}
