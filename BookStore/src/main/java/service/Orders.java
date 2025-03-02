package service;

import exceptions.CartNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Orders {
    private Long orderId;
    private Long customerId;
    private static final Carts cart = new Carts();
    private static final Map<Long, Map<Long, Integer>> orders = new HashMap<>();

    public void placeOrder(Long orderId, Long customerId) throws CartNotFoundException {
        Map <Long, Integer> cartItems = cart.getCart(customerId);
        if(cartItems.isEmpty()) {
            throw new CartNotFoundException("Cart not found");
        }
        orders.put(orderId, cartItems);
    }


}
