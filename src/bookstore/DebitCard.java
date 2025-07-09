package bookstore;

public class DebitCard extends Payment {

    public DebitCard(int orderId, int paymentId, double amount) {
        super(orderId, paymentId, amount);
    }

    // Operation: validatePayment for DebitCard
    // Precondition: Amount must be greater than 0
    // Postcondition: Returns a valid or invalid Receipt based on amount
    @Override
    public Receipt validatePayment() {
        if (this.amount <= 0) {
            return new Receipt(false, "Payment failed: amount must be greater than zero.");
        }
        return new Receipt(true, "Payment successful via DebitCard.");
    }
}