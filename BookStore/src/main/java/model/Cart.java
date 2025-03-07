package model;

import java.util.ArrayList;

public class Cart {
    private Long customerId;
    private ArrayList<Book> books;
    private int quantity;

    public Cart() {
        books = new ArrayList<>();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBook(Book book) {
        books.add(book);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
