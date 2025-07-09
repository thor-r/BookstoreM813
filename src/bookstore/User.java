package bookstore;

public abstract class User {
    protected String name;
    protected String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters (optional)
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Abstract methods (if needed)
}
