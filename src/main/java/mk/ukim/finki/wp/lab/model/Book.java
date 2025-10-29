package mk.ukim.finki.wp.lab.model;
import  lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class Book {
    private String title;
    private String genre;
    private double averageRating;
}
