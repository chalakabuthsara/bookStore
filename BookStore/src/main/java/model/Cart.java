package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Long customerId;
    private Map<Long, Integer> books;
    private int totalQuantity;

    public Cart() {
        books = new HashMap<>();
    }

    public void setBooks(Long bookId, int quantity) {
        books.put(bookId, quantity);
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Map<Long, Integer> getBooks() {
        return books;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
