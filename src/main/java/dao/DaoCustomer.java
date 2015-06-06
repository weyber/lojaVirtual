package dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Customer;

@Repository
@Transactional
public class DaoCustomer implements IDaoCustomer, Serializable {

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Customer select(String username){
        Query query = 
                em.createQuery("FROM Customer u "
                        + "WHERE u.username = :login");
        query.setParameter("login", username);
        return (Customer) query.getSingleResult();
    }

    /* SELECT CUSTOMER */
    @Override
    public boolean login(String login, String password) {
        boolean b;
        try {
            Query query = 
                    em.createQuery("FROM Customer u "
                            + "WHERE u.username = :login "
                            + "AND u.password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            query.getSingleResult();

            b = true;
        } catch (NoResultException e) {
            b = false;
        }
        return b;
    }

    @Override
    public boolean exist(String login) {
        boolean b;
        try {
            Query query = 
                    em.createQuery("FROM Customer u "
                            + "WHERE u.username = :login");
            query.setParameter("login", login);
            query.getSingleResult();

            b = true;
        } catch (NoResultException e) {
            b = false;
        }
        return b;
    }  
    
}
