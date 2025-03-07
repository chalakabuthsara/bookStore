package model;

import java.util.ArrayList;

public class Order {
    private Long orderId;
    private Long customerId;
    private ArrayList<Book> bookOrders;
    private double totalPrice;

    public Order() {}

    public Order(Long orderId, Long customerId, ArrayList<Book> bookOrders, double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.bookOrders = bookOrders;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public ArrayList<Book> getBookOrders() {
        return bookOrders;
    }

    public void setBookOrders(ArrayList<Book> bookOrders) {
        this.bookOrders = bookOrders;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
