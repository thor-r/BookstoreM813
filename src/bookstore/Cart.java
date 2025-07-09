package bookstore;

import java.util.*;

public class Cart {
    // Attributes
    private int quantity;
    private double cost;
    private Map<Book, Integer> books;

    // Associations
    private Customer customer;
    private Order order;

    // Constructor
    public Cart(int quantity, double cost, List<Book> bookList, Customer customer) {
        this.quantity = quantity;
        this.cost = cost;
        this.customer = customer;
        this.books = new HashMap<>();
        for (Book b : bookList) {
            books.put(b, 1); // assuming quantity = 1 per book
        }
    }
    public boolean isEmpty() {
        return books == null || books.isEmpty();
    }

    public Map<Book, Integer> getBooks() {
        return books;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    // Operation: create
    // Precondition: Cart has valid data for order creation
    // Postcondition: Returns a new CurrentOrder or Reservation with provided data
    public Order create(int id, Date date, int quantity, double cost, List<Book> bookList, boolean orderCancelled) {
        for (Book book : bookList) {
            if (book.getCurrentStock() == 0 && book.isReservable()) {
                return new Reservation(bookList, quantity, cost, book.getTitle(), book.getIsbn(), new Date());
            }
        }
        return new CurrentOrder(bookList, quantity, cost);
    }

    // Helper methods
    public double totalCost() {
        return cost;
    }

    public int totalQuantity() {
        return quantity;
    }

    public int generateOrderId() {
        return new Random().nextInt(100000);
    }

    public int restockThreshold() {
        return 2;
    }
}