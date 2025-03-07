package service;

import exceptions.BookNotFoundException;
import exceptions.CartNotFoundException;
import exceptions.CustomerNotFoundException;
import exceptions.OutOfStockException;
import model.Book;
import model.Cart;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CartService {
    private static CartService instance;
    private ArrayList<Cart> carts;
    private BookService bookService = BookService.getInstance();
    private CustomerService customerService = CustomerService.getInstance();

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

    public void addItem(Long customerId, Long bookId) throws BookNotFoundException, OutOfStockException, CustomerNotFoundException {
        Cart cart;
        try {
           cart = getCart(customerId);
       } catch (CartNotFoundException e) {
           cart = new Cart();
           cart.setCustomerId(customerId);
           carts.add(cart);
       }
       Book book = bookService.getBook(bookId);
       if(book.getStockQuantity() == 0) {
           throw new OutOfStockException("Book " + bookId + " is out of stock");
       }
       cart.setBook(book);
       cart.setQuantity(cart.getQuantity() + 1);
    }

    public ArrayList<Book> getItems(Long customerId) throws CustomerNotFoundException, CartNotFoundException {
        Cart cart = getCart(customerId);
        return cart.getBooks();
    }

    public Book updateItem(Long customerId, Book updatedBook, Long bookId) throws CustomerNotFoundException, CartNotFoundException, BookNotFoundException {
        for(Book book: getItems(customerId)) {
            if(book.getBookId().equals(bookId)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthorId(updatedBook.getAuthorId());
                book.setIsbn(updatedBook.getIsbn());
                book.setPrice(updatedBook.getPrice());
                book.setPublicationYear(updatedBook.getPublicationYear());
                book.setStockQuantity(updatedBook.getStockQuantity());
                return book;
            }
        }
        throw new BookNotFoundException("Book with" + bookId + " not found");
    }

    public void deleteItem(Long customerId, Long bookId) throws CustomerNotFoundException, CartNotFoundException, BookNotFoundException {
        ArrayList<Book> items = getItems(customerId);
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getBookId().equals(bookId)) {
                items.remove(i);
                return;
            }
        }
        throw new BookNotFoundException("Book " + bookId + " not found");
    }

    public void clearItems(Long customerId) throws CustomerNotFoundException, CartNotFoundException {
        carts.remove(getCart(customerId));
    }

}
