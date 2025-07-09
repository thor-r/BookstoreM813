package bookstore;

import java.util.List;

public class CurrentOrder extends Order {

    public CurrentOrder(List<Book> books, int quantity, double cost) {
        super(books, quantity, cost);
    }

    // No additional attributes
}
