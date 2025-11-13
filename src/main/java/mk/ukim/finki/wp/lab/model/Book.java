package mk.ukim.finki.wp.lab.model;
import  lombok.*;

@Data @NoArgsConstructor
public class Book {
    private String title;
    private String genre;
    private double averageRating;
    private Long id;
    private Author author;

    public Book(String title, String genre, double averageRating, Long id, Author author) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.id = (long) (Math.random() * 1000);
        this.author = author;
    }

}
