package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Orders implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(value = TemporalType.DATE)
    private Date dateRequest = new Date();
    
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy="orders", cascade=CascadeType.ALL, 
            fetch = FetchType.EAGER)
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Collection<Item> items;
    
    /* Constructor */
    public Orders() {
        items = new ArrayList<Item>();
    }
    
    /* Gets and Sets */
    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }
    
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public Date getDateRequest() {
        return dateRequest;
    }
}