package bookstore;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    // Attributes
    private String customerID;
    private String address;
    private String phoneNumber;

    // Associations
    private List<Order> pastOrders; // ✅ New attribute

    public Customer(
            String name,
            String email,
            String customerID,
            String address,
            String phoneNumber
    ) {
        super(name, email);
        this.customerID = customerID;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pastOrders = new ArrayList<>();
    }

    // Postcondition: Associates an order with the customer
    public void addPastOrder(Order order) {
        this.pastOrders.add(order);
    }


    // Optional: for retrieval or testing
    public List<Order> getPastOrders() {
        return pastOrders;
    }

    // Operation: create
    // Postcondition: Returns a new Cart instance with selected books
    public Cart create(int quantity, double totalCost, List<Book> books) {
        return new Cart(quantity, totalCost, books, this);
    }

    // Getters for existing attributes
    public String getCustomerID() { return customerID; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }

}