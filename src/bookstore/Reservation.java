package bookstore;

import java.util.Date;
import java.util.List;

public class Reservation extends Order {
    private String title;
    private long isbn;
    private Date releaseDate;

    public Reservation(List<Book> books, int quantity, double cost, String title, long isbn, Date releaseDate) {
        super(books, quantity, cost);
        this.title = title;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public long getIsbn() {
        return isbn;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
