package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text, rating);
    }

    @Override
    public void saveBook(String title, String genre, Double averageRating, Long authorId) {
        Author author = authorRepository.findAll().stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst()
                .orElse(null);

        Book newBook = new Book(title, genre, averageRating, null, author);
        bookRepository.save(newBook);
    }

    @Override
    public void editBook(Long bookId, String title, String genre, Double averageRating, Long authorId) {
        Author author = authorRepository.findAll().stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst()
                .orElse(null);

        bookRepository.findAll().stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .ifPresent(b -> {
                    b.setTitle(title);
                    b.setGenre(genre);
                    b.setAverageRating(averageRating);
                    b.setAuthor(author);
                });
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
