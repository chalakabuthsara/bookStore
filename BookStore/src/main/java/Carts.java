import java.util.HashMap;
import java.util.Map;

public class Carts {
    private Long customerId;
    private Map<Long, Integer> books;

    public Carts(Long customerId, Map<Long, Integer> books) {
        this.customerId =customerId;
        this.books = new HashMap<Long, Integer>();
    }

    public void addBook(Long bookId, int quantity) {
        books.put(bookId, quantity);
    }

    public void removeBook(Long bookId, int quantity) {
        books.remove(bookId, quantity);
    }

    public void updateBook(Long bookId, int quantity) {
        if(books.containsKey(bookId)) {
            books.put(bookId, quantity);
        }
    }
}
