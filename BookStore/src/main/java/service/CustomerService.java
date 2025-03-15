package service;

import exceptions.BookNotFoundException;
import exceptions.CustomerNotFoundException;
import exceptions.InvalidInputException;
import model.Author;
import model.Book;
import model.Customer;

import java.util.ArrayList;

public class CustomerService {
    private static final String EMAIL_REGEX = "^[^@]+@[^@]+\\.[^@]+$";
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

    public void addCustomer(Customer customer) throws InvalidInputException {
        validateCustomer(customer);
        for (Customer existingCustomer : customers) {
            if (existingCustomer.getEmail().equalsIgnoreCase(customer.getEmail())) {
                throw new InvalidInputException("Email already in use.");
            }
        }
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

    public Customer updateCustomer(Long id, Customer updatedCustomer) throws CustomerNotFoundException, InvalidInputException {
        validateCustomer(updatedCustomer);

        for (Customer existingCustomer : customers) {
            if (!existingCustomer.getCustomerId().equals(id) && existingCustomer.getEmail().equalsIgnoreCase(updatedCustomer.getEmail())) {
                throw new InvalidInputException("Email already in use.");
            }
        }
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

    public void validateCustomer(Customer customer) throws InvalidInputException {
        if (customer == null) {
            throw new InvalidInputException("Customer information is missing.");
        }
        for (Customer existingCustomer : customers) {
            if (existingCustomer.getCustomerId().equals(customer.getCustomerId())) {
                throw new InvalidInputException("Customer ID already exists.");
            }
        }

        if (customer.getCustomerId() == null || customer.getCustomerId().toString().trim().isEmpty()) {
            throw new InvalidInputException("customerId shouldn't be empty.");
        }

        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            throw new InvalidInputException("Customer's first name shouldn't be empty.");
        }

        if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
            throw new InvalidInputException("Customer's last name shouldn't be empty.");
        }

        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email shouldn't be empty.");
        }

        if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Password shouldn't be empty.");
        }
        if (!customer.getEmail().matches(EMAIL_REGEX)) {
            throw new InvalidInputException("Invalid email format.");
        }
    }

}
