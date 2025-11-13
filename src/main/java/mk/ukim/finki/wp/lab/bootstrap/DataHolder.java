package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    // kons za da se injektirat dvete repos inace mi mava crveno ne znam sod a praam
    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        List<Author> authors = authorRepository.findAll();

        books = List.of(
                new Book("Harry Potter", "Fantasy", 4.8, null, authors.get(0)),
                new Book("The Hobbit", "Adventure", 4.7, null, authors.get(1)),
                new Book("1984", "Dystopian", 4.6, null, authors.get(2)),
                new Book("To Kill a Mockingbird", "Classic", 4.9, null, authors.get(0)),
                new Book("Pride and Prejudice", "Romance", 4.5, null, authors.get(1)),
                new Book("The Great Gatsby", "Classic", 4.3, null, authors.get(2)),
                new Book("The Catcher in the Rye", "Classic", 4.1, null, authors.get(0)),
                new Book("The Shining", "Horror", 4.2, null, authors.get(1)),
                new Book("The Da Vinci Code", "Mystery", 4.0, null, authors.get(2)),
                new Book("Moby Dick", "Adventure", 3.9, null, authors.get(0))
        );

        books.forEach(bookRepository::save);
    }
}
