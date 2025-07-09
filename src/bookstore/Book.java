package bookstore;

public class Book {

    // Attributes
    private String title;
    private String author;
    private long isbn;
    private double price;
    private BookGenre genre;
    private BookType type;
    private String publisher;
    private boolean signedByAuthor;
    private int currentStock;

    public Book(String title, String author, long isbn, double price,
                BookGenre genre, BookType type, String publisher,
                boolean signedByAuthor, int currentStock) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.genre = genre;
        this.type = type;
        this.publisher = publisher;
        this.signedByAuthor = signedByAuthor;
        this.currentStock = currentStock;
    }

    public boolean isReservable() {
        return this.type == BookType.LIMITED_EDITION || this.type == BookType.SPECIAL_EDITION;
    }

    // Getters (you can generate all if needed)
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public long getIsbn() { return isbn; }
    public double getPrice() { return price; }
    public BookGenre getGenre() { return genre; }
    public BookType getType() { return type; }
    public String getPublisher() { return publisher; }
    public boolean isSignedByAuthor() { return signedByAuthor; }
    public int getCurrentStock() { return currentStock; }
}