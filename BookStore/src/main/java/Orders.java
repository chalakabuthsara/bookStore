import java.util.HashMap;
import java.util.Map;

public class Orders {
    private Long orderId;
    private Long customerId;
    private Map<Long, Integer> orders;

    public Orders(Long orderId, Long customerId, Map<Long, Integer> books) {
        this.orderId = orderId;
        this.customerId =customerId;
        this.orders = new HashMap<Long, Integer>();
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

    public Map<Long, Integer> getOrders() {
        return orders;
    }

    public void setOrders(Map<Long, Integer> orders) {
        this.orders = orders;
    }
}
