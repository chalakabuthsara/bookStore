package service;

import java.util.ArrayList;
import java.util.Map;

import exceptions.BookNotFoundException;
import exceptions.CartNotFoundException;
import exceptions.CustomerNotFoundException;
import exceptions.OrderNotFoundException;
import exceptions.OutOfStockException;
import model.Book;
import model.Cart;
import model.Order;

public class OrderService {
    private static OrderService instance;
    private ArrayList<Order> orders;
    private final CartService cartService = CartService.getInstance();
    private final BookService bookService = BookService.getInstance();
    private Long orderCounter = 1L;

    private OrderService() {
        orders = new ArrayList<>();
    }

    public static OrderService getInstance() {
        if(instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public Order placeOrder(Long customerId)
            throws CustomerNotFoundException, CartNotFoundException, OutOfStockException, BookNotFoundException {
        Cart cart = cartService.getCart(customerId);
        Map<Long, Integer> items = cart.getBooks();
        Long orderId = orderCounter++;
        double totalPrice = 0;

        for (Long bookId : items.keySet()) {
            Book book = bookService.getBook(bookId);
            int quantity = items.get(bookId);
            if (book == null) {
                throw new BookNotFoundException("Book with id " + bookId + " not found.");
            }
            if (book.getStockQuantity() < quantity) {
                throw new OutOfStockException("Not enough stock for book with id: " + bookId);
            }

            totalPrice += book.getPrice() * quantity;
        }

        for (Long bookId : items.keySet()) {
            Book book = bookService.getBook(bookId);
            int quantity = items.get(bookId);
            book.setStockQuantity(book.getStockQuantity() - quantity);
        }
        Order order = new Order(orderId, customerId, items, totalPrice);
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
            if(order.getOrderId().equals(orderId) && order.getCustomerId().equals(customerId)) {
                return order;
            }
        }
        throw new OrderNotFoundException("Order with ID " + orderId + " for customer " + customerId + " not found");
    }
}
