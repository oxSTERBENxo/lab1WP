package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final List<Book> books = new ArrayList<>();

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public void deleteById(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return books.stream()
                .filter(book ->
                        (text == null || text.isEmpty() || book.getTitle().toLowerCase().contains(text.toLowerCase())) &&
                                (rating == null || book.getAverageRating() >= rating))
                .toList();
    }

}
