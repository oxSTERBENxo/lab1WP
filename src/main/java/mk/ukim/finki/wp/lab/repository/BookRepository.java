package mk.ukim.finki.wp.lab.repository;
import mk.ukim.finki.wp.lab.model.*;
import java.util.*;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    void save(Book book);
    void deleteById(Long id);

}
