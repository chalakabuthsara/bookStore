package service;

import exceptions.BookNotFoundException;
import exceptions.CustomerNotFoundException;
import model.Book;
import model.Customer;

import java.util.ArrayList;

public class CustomerService {
    private static CustomerService instance;
    private ArrayList<Customer> customers;

    private CustomerService() {
        customers = new ArrayList<>();
    }

    public static CustomerService getInstance() {
        if(instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(Long id) throws CustomerNotFoundException {
        for(Customer customer: customers) {
            if(customer.getCustomerId().equals(id)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer " + id + " not found");
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) throws CustomerNotFoundException {
        for(Customer customer: customers) {
            if(customer.getCustomerId().equals(id)) {
                customer.setFirstName(updatedCustomer.getFirstName());
                customer.setLastName(updatedCustomer.getLastName());
                customer.setEmail(updatedCustomer.getEmail());
                customer.setPassword(updatedCustomer.getPassword());
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer " + id + " not found");
    }

    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equals(id)) {
                customers.remove(i);
                return;
            }
        }
        throw new CustomerNotFoundException("Customer " + id + " not found");
    }

}
