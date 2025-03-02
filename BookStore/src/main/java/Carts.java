import java.util.HashMap;
import java.util.Map;

public class Carts {
    private Long customerId;
    private static final Map<Long, Map<Long, Integer>> carts = new HashMap<>();

    public Map<Long,Integer> getCart(Long customerId) {
        if(!carts.containsKey(customerId)) {
            carts.put(customerId, new HashMap<>());
        }
        return carts.get(customerId);
    }

    public void addBook(Long customerId, Long bookId, int quantity) {
        Map<Long, Integer> cart = getCart(customerId);
        cart.put(bookId, quantity);
    }

    public void removeBook(Long customerId, Long bookId, int quantity) {
        Map<Long, Integer> cart = getCart(customerId);
        cart.remove(bookId, quantity);
    }

    public void updateBook(Long customerId, Long bookId, int quantity) {
        Map<Long, Integer> cart = getCart(customerId);
        if(cart.containsKey(bookId)) {
            cart.put(bookId, cart.getOrDefault(bookId, 0) + quantity);
        }
        else {
            throw new IllegalArgumentException("Book is not in cart");
        }
    }
}
