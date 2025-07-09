package bookstore;

import java.util.Date;
import java.util.List;
import java.util.UUID;

// Abstract class representing order types
public abstract class Order {

    // Attributes
    protected String id;
    protected Date date;
    protected int quantity;
    protected double cost;
    protected List<Book> books;
    protected boolean orderCancelled;

    // Associations
    protected Customer customer; // optional
    protected Payment payment;

    public Order(List<Book> books, int quantity, double cost) {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.books = books;
        this.quantity = quantity;
        this.cost = cost;
        this.orderCancelled = false;

        // Associations initially nul, set externally as needed
        this.customer = null;
        this.payment = null;
    }

    // Postcondition for processSale - Associates payment with the order
    public Payment create(double totalCost, int orderId, int paymentId) {
        return new DebitCard(orderId, paymentId, totalCost);
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    public List<Book> getBooks() {
        return books;
    }

    public boolean isOrderCancelled() {
        return orderCancelled;
    }

    public void cancelOrder() {
        this.orderCancelled = true;
    }
}