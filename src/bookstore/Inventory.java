package bookstore;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    // Attributes
    private Map<Book, Integer> stock = new HashMap<>();

    public void addBook(Book book, int quantity) {
        stock.put(book, quantity);
    }

    // Operation: stockCheck
    // Postcondition: Returns current stock for the specified book
    public int stockCheck(Book book) {
        return stock.getOrDefault(book, 0);
    }

    // Operation: updateStock
    // Postcondition: Decreases current stock for each book after sale
    public void updateStock(int restockThreshold, Map<Book, Integer> cartBooks) {
        for (Book book : cartBooks.keySet()) {
            int quantity = cartBooks.get(book);
            int current = stockCheck(book);
            stock.put(book, current - quantity);
            // Optionally trigger restocking if (current - quantity) < restockThreshold
        }
    }
}