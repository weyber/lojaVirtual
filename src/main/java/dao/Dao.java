package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Book;
import model.Item;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class Dao implements IDao, Serializable  {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    /* INSERT */
    @Override
    public void insert(Object object){
        em.persist(object);
    }

    @Override
    public List<Item> select(String login) {
        TypedQuery<Item> query =
                em.createQuery(
                        "from Item a where "
                        + "a.orders.customer.username "
                        + "like :login", Item.class);
                query.setParameter("login", login);
        return query.getResultList();
    }
    
    @Override
    public List<Book> selectBooks(String search) {
        TypedQuery<Book> query = 
                em.createQuery("From Book b "
                        + "Where b.title LIKE :search", Book.class);
                query.setParameter("search", "%" + search + "%");
        return query.getResultList();
    }
    
    /* SELECT */
    @Override
    public List<Book> select() {  
        TypedQuery<Book> query =
                em.createQuery("FROM Book b", Book.class);
        return query.getResultList();        
    }

}
