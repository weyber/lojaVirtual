package bean;

import model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import utilFaces.UtilFaces;
import dao.IDao;
import dao.IDaoCustomer;

@Controller
@Scope("request")
public class LoginBean {
    private Customer customer;
    
    @Autowired
    private IDaoCustomer daoCustomer;
    
    @Autowired
    private IDao dao;
    
    @Autowired
    ItemBean finalizeBean;
    
    /* Constructor */ 
    public LoginBean() {
        customer = new Customer();
    }
    
    public String createCustomer() {
        boolean b = daoCustomer.exist(customer.getUsername());

        if (!b) {
            finalizeBean.setUser(customer.getUsername());
            dao.insert(customer);
            return "index?faces-redirect=true";
        }
        else {
            UtilFaces.save("User already registered.");
        }
        return "";
    }
    
    public String login() {
        boolean b = daoCustomer.login(customer.getUsername(), 
                              customer.getPassword());

        if (b) {
            finalizeBean.setUser(customer.getUsername());
          return "index?faces-redirect=true";
        } else {
            UtilFaces.save("User not registered !");
        }
        return "";
    }

    /* Gets and Sets */
    public Customer getCustomer() {
        return customer;
    }
}
