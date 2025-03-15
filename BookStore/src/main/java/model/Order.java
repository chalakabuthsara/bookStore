package model;

import java.util.ArrayList;
import java.util.Map;

public class Order {
    private Long orderId;
    private Long customerId;
    private Map<Long, Integer> bookOrders;
    private double totalPrice;

    public Order() {}

    public Order(Long orderId, Long customerId, Map<Long, Integer> bookOrders, double totalPrice) {
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

    public  Map<Long, Integer> getBookOrders() {
        return bookOrders;
    }

    public void setBookOrders(Map<Long, Integer> bookOrders) {
        this.bookOrders = bookOrders;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
