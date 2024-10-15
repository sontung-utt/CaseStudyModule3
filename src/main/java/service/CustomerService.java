package service;

import model.Customer;

import java.util.List;

public class CustomerService implements IService<Customer>{
    @Override
    public void add(Customer customer) {

    }

    @Override
    public void update(int id, Customer customer) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }
}
