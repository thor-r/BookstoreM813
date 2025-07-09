package bookstore;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Bookstore {

    // Attributes
    private String name;
    private String address;

    // Constructor
    public Bookstore(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * System Operation: processSale
     * Identifier: SO 01, Version 1.1
     * Context: Bookstore
     * Signature: processSale(cart: Cart, payment: Payment, customer?: CustomerId): Order
     * Invariant: true
     */
    public Order processSale(Cart cart, Payment payment, String customerId, Customer customer, Inventory inventory) {

        // Precondition: The Cart contains at least one Book
        if (cart == null || cart.isEmpty())
            throw new IllegalArgumentException("Empty cart.");

        // Precondition: The payment is valid
        // Bookstore needs to validate the payment before proceeding.
        Receipt receipt = payment.validatePayment();
        if (!receipt.isValid()) {
            throw new IllegalArgumentException("Invalid payment.");
        }

        // Precondition: Books in the cart have sufficient stock OR reservation is allowed
        for (Book book : cart.getBooks().keySet()) {
            int qty = cart.getBooks().get(book);
            if (inventory.stockCheck(book) < qty && !book.isReservable()) {
                throw new IllegalArgumentException("Not enough stock for: " + book.getTitle());
            }
        }

        // Postcondition: A new Order instance is created and returned,
        // including correct quantity, totalCost, date is today, orderCancelled is false
        Order order = cart.create(
                cart.generateOrderId(),
                new Date(),
                cart.totalQuantity(),
                cart.totalCost(),
                new ArrayList<>(cart.getBooks().keySet()),
                false
        );
        cart.setOrder(order);

        // Postcondition: The Inventory is decremented accordingly
        inventory.updateStock(cart.restockThreshold(), cart.getBooks());

        // Association: Link Order to Customer if customerId is available
        if (customer != null) {
            customer.addPastOrder(order);
        }

        // Postcondition: A new Payment instance is linked to the Order (via Payment.create, handled elsewhere)
        // External responsibilities (postconditions not implemented here):
        // Sales report is updated/recorded
        // A receipt and confirmation notification are generated

        return order;
    }

    public String getName() { return name; }

    public String getAddress() { return address; }
}