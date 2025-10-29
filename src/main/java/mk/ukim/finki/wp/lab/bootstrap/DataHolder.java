package mk.ukim.finki.wp.lab.bootstrap;
import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init(){
        books.add(new Book("Harry Potter", "Fantasy", 4.8));
        books.add(new Book("The Hobbit", "Adventure", 4.7));
        books.add(new Book("1984", "Dystopian", 4.6));
        books.add(new Book("To Kill a Mockingbird", "Classic", 4.9));
        books.add(new Book("Pride and Prejudice", "Romance", 4.5));
        books.add(new Book("The Great Gatsby", "Classic", 4.3));
        books.add(new Book("The Catcher in the Rye", "Classic", 4.1));
        books.add(new Book("The Shining", "Horror", 4.2));
        books.add(new Book("The Da Vinci Code", "Mystery", 4.0));
        books.add(new Book("Moby Dick", "Adventure", 3.9));
    }
}
