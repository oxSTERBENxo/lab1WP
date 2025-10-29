package mk.ukim.finki.wp.lab.model;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReservation {

    private String bookTitle;
    private String readerName;
    private String readerAddress;
    private Long numberOfCopies;

}
