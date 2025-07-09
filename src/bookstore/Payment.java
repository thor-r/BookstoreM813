package bookstore;

// Abstract class representing a payment
public abstract class Payment {
    protected int orderId;
    protected int paymentId;
    protected double amount;

    public Payment(int orderId, int paymentId, double amount) {
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.amount = amount;
    }

    // Operation: validatePayment
    // Postcondition: Returns a Receipt object indicating if the payment is valid
    public abstract Receipt validatePayment();

    public double getAmount() {
        return amount;
    }

    public int getPaymentId() {
        return paymentId;
    }
}