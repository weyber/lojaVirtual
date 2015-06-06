package dao;

import model.Customer;

public interface IDaoCustomer {
    
    public boolean login(String login, String password);
    
    public boolean exist(String login);
    
    public Customer select(String username);
    
}
