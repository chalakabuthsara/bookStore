package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import exceptions.BookNotFoundException;
import exceptions.CartNotFoundException;
import exceptions.CustomerNotFoundException;
import exceptions.InvalidInputException;
import exceptions.OutOfStockException;
import model.Book;
import model.Cart;

public class CartService {
    private static CartService instance;
    private final ArrayList<Cart> carts;
    private final BookService bookService = BookService.getInstance();
    private final CustomerService customerService = CustomerService.getInstance();

    private CartService() {
        carts = new ArrayList<>();
    }

    public static CartService getInstance(){
        if(instance == null) {
            instance = new CartService();
        }
        return instance;
    }

    public Cart getCart(Long customerId) throws CustomerNotFoundException, CartNotFoundException {
        if(customerService.getCustomer(customerId) == null) {
            throw new CustomerNotFoundException("Customer " + customerId + " not found");
        }
        for (Cart cart: carts) {
            if(cart.getCustomerId().equals(customerId)) {
                return cart;
            }
        }
        throw new CartNotFoundException("Cart not found");
    }

    public void addItem(Long customerId, Long bookId, int quantity) throws BookNotFoundException, OutOfStockException, CustomerNotFoundException, InvalidInputException {
        Cart cart;
        try {
            cart = getCart(customerId);
        } catch (CartNotFoundException e) {
            cart = new Cart();
            cart.setCustomerId(customerId);
            carts.add(cart);
        }

        if(quantity<= 0) {
            throw new InvalidInputException("Quantity shouldn't be zero or negative.");
        }

        Book book = bookService.getBook(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }

        if (book.getStockQuantity() <= 0) {
            throw new OutOfStockException("Book " + bookId + " is out of stock.");
        }

        int cartQuantity = 0;
        if (cart.getBooks().containsKey(bookId)) {
            cartQuantity = cart.getBooks().get(bookId);
        }

        if (cartQuantity + quantity > book.getStockQuantity()) {
            throw new OutOfStockException("Cannot add more copies of book " + bookId + " than the available stock.");
        }

        cart.getBooks().put(bookId, cartQuantity + quantity);

        cart.setTotalQuantity(cart.getTotalQuantity() + quantity);
    }


    public Map<String, Integer> getItems(Long customerId) throws CustomerNotFoundException, CartNotFoundException, BookNotFoundException {
        Cart cart = getCart(customerId);
        Map<Long, Integer> cartItems = cart.getBooks();
        Map<String, Integer> books = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : cartItems.entrySet()) {
            String title = bookService.getBook(entry.getKey()).getTitle();
            books.put(title, entry.getValue());
        }
        return books;
    }

    public void updateItem(Long customerId, Long bookId, int newQuantity) throws CustomerNotFoundException, CartNotFoundException, BookNotFoundException, InvalidInputException, OutOfStockException {
        Cart cart = getCart(customerId);
        if (!cart.getBooks().containsKey(bookId)) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found in the cart.");
        }
        Book book = bookService.getBook(bookId);
        if (newQuantity <= 0) {
            throw new InvalidInputException("Quantity cannot be zero or negative.");
        }
        if (newQuantity > book.getStockQuantity()) {
            throw new OutOfStockException("Not enough stock available for book " + bookId);
        }
        int currentQuantity = cart.getBooks().get(bookId);

        cart.getBooks().put(bookId, newQuantity);

        int totalQuantityChange = newQuantity - currentQuantity;
        cart.setTotalQuantity(cart.getTotalQuantity() + totalQuantityChange);


        if (newQuantity == 0) {
            cart.getBooks().remove(bookId);
            cart.setTotalQuantity(cart.getTotalQuantity() - currentQuantity);
        }
    }


    public void deleteItem(Long customerId, Long bookId) throws CustomerNotFoundException, CartNotFoundException, BookNotFoundException {
        Cart cart = getCart(customerId);
        if (!cart.getBooks().containsKey(bookId)) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found in the cart.");
        }

        int quantity = cart.getBooks().get(bookId);

        cart.getBooks().remove(bookId);

        cart.setTotalQuantity(cart.getTotalQuantity() - quantity);
    }


    public void clearItems(Long customerId) throws CustomerNotFoundException, CartNotFoundException {
        carts.remove(getCart(customerId));
    }

}
