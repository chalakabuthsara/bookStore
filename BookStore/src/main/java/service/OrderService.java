package service;

import exceptions.CartNotFoundException;
import exceptions.CustomerNotFoundException;
import exceptions.OrderNotFoundException;
import model.Book;
import model.Cart;
import model.Order;

import java.util.ArrayList;

public class OrderService {
    private static OrderService instance;
    private ArrayList<Order> orders;
    private CartService cartService = CartService.getInstance();
    private Long orderCounter = 0L;

    private OrderService() {
        orders = new ArrayList<>();
    }

    public static OrderService getInstance() {
        if(instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public Order placeOrder( Long customerId) throws CustomerNotFoundException, CartNotFoundException {
        ArrayList<Book> books = new ArrayList<>(cartService.getCart(customerId).getBooks());
        Long orderId = orderCounter++;
        double totalPrice = 0;
        for(Book book: books) {
            totalPrice += book.getPrice();
            book.setStockQuantity(book.getStockQuantity()-1);
        }
        Order order = new Order(orderId, customerId, books, totalPrice);
        orders.add(order);
        cartService.clearItems(customerId);
        return order;
    }

    public ArrayList<Order> getOrders(Long customerId) throws OrderNotFoundException {
        ArrayList<Order> customerOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getCustomerId().equals(customerId)) {
                customerOrders.add(order);
            }
        }
        if (customerOrders.isEmpty()) {
            throw new OrderNotFoundException("No orders found for customer " + customerId);
        }

        return customerOrders;
    }

    public Order getOrder( Long customerId, Long orderId) throws OrderNotFoundException {
        for(Order order: orders) {
            if(order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        throw new OrderNotFoundException("Order for the customer " + customerId + " not found");
    }
}
