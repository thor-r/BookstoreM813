package bookstore;

public class Receipt {
    private boolean valid;
    private String message;

    public Receipt(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}