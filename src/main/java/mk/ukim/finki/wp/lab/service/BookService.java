package mk.ukim.finki.wp.lab.service;
import mk.ukim.finki.wp.lab.model.Book;
import java.util.*;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    void saveBook(String title, String genre, Double averageRating, Long authorId);
    void editBook(Long bookId, String title, String genre, Double averageRating, Long authorId);
    void deleteBook(Long id);

    interface BookReservationService {
    }
}
