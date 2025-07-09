package bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class BookstoreTest {

    //fixture declarations
    Book book1;
    Customer customer;

    @BeforeEach
    public void setUp() {
        //fixture setUp
        book1 = new Book(
                "Hiero's Journey",
                "Sterling E. Lanier",
                9780132350884L,
                15.0,
                BookGenre.SCIENCE_FICTION,
                BookType.PAPERBACK,
                "Gollancz",
                false,
                10
        );

        // Add a customer
        customer = new Customer(
                "Thor",
                "thor@email.com",
                "CUST1",
                "Earth",
                "123456789"
        );
    }

    // Test Case: Valid scenario
    // Preconditions:
    // - Cart contains one valid book
    // - Payment is valid
    // - Inventory has sufficient stock
    // Postconditions:
    // - A new CurrentOrder is created
    // - Order is not cancelled
    // - Order has correct total cost
    @Test
    public void testProcessSale_ValidScenario() {
        // Arrange: Create customer, book, cart, inventory, and valid payment
        String addr = "1 Main St, Oxford, OX1 1AA";

        Customer customer = new Customer(
                "Alice",
                "alice@email.com",
                "CUST2",
                "Oxford",
                "123456789"
        );

        List<Book> books = List.of(
                new Book(
                        "Clean Code",
                        "Robert Martin",
                        9780132350884L,
                        15.0,
                        BookGenre.NON_FICTION,
                        BookType.PAPERBACK,
                        "Prentice Hall",
                        false,
                        5
                )
        );

        Cart cart = customer.create(1, 50.0, books);

        Inventory inventory = new Inventory();

        inventory.addBook(books.get(0), 5);

        Payment payment = new DebitCard(10001, 20002, 15.0);

        Bookstore bookstore = new Bookstore("TechBooks", addr);

        Order order = bookstore.processSale(
                cart,
                payment,
                "CUST123",
                customer,
                inventory
        );

        assertTrue(order instanceof CurrentOrder);
        assertEquals(50.0, order.getCost());
        assertFalse(order.isOrderCancelled());
    }

    // Test Case: Invalid Payment
    // Preconditions:
    // - Cart contains valid book
    // - Payment is invalid (zero balance)
    // Postconditions:
    // - IllegalArgumentException is thrown due to invalid payment
    @Test
    public void testProcessSale_InvalidPayment() {

        // Arrange: Create cart with valid book and invalid payment
        Cart cart = customer.create(1, 50.0, List.of(book1));

        Payment invalidPayment = new DebitCard(0, 0, 0.0); // invalid payment with zero balance
        Inventory inventory = new Inventory();

        inventory.addBook(book1, book1.getCurrentStock());

        Bookstore bookstore = new Bookstore("DefaultStore", "Default Address");

        assertThrows(IllegalArgumentException.class, () -> {
            bookstore.processSale(cart, invalidPayment, "1", customer, inventory);
        });
    }

    // Test Case: Insufficient Stock
    // Preconditions:
    // - Cart contains one valid book and one with zero stock
    // - Payment is valid
    // Postconditions:
    // - IllegalArgumentException is thrown due to insufficient stock
    @Test
    public void testProcessSale_InsufficientStock() {

        // Arrange: Create cart with one in-stock book and one out-of-stock book
        Book insufficientStockBook = new Book(
                "The Pragmatic Programmer",
                "Andrew Hunt & David Thomas",
                9780201616224L,
                40.0,
                BookGenre.NON_FICTION,
                BookType.HARDCOVER,
                "Addison-Wesley",
                false,
                0 // for test of insufficient stock
        );

        Cart cart = customer.create(1, 90.0, List.of(book1, insufficientStockBook));

        Inventory inventory = new Inventory();

        inventory.addBook(book1, book1.getCurrentStock());

        inventory.addBook(insufficientStockBook, insufficientStockBook.getCurrentStock());

        Payment payment = new DebitCard(12345, 67890, 100.0);

        Bookstore bookstore = new Bookstore("DefaultStore", "Default Address");

        assertThrows(IllegalArgumentException.class, () -> {
            bookstore.processSale(cart, payment, "1", customer, inventory);
        });
    }

}
