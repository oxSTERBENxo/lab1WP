package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.LinkedList;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    //@GetMapping("/books")
//    public String getBooksPage(Model model, HttpSession session) {
//        model.addAttribute("books", bookService.listAll());
//        return "listBooks";
//    }
    @GetMapping("/books")
    public String getBooksPage(Model model, HttpSession session) {

        List<Book> allBooks = bookService.listAll();
        List<Book> books = new LinkedList<>(allBooks);

        //za da nema zolto neshto idk
        @SuppressWarnings("unchecked")
        List<Long> lastReservedIds = (List<Long>) session.getAttribute("lastReservedIds");

        //remove da book
        if (lastReservedIds != null && !lastReservedIds.isEmpty()) {
            books.removeIf(book -> lastReservedIds.contains(book.getId()));

            //add at the end so order rememebreing
            for (Long id : lastReservedIds) {
                allBooks.stream()
                        .filter(b -> b.getId().equals(id))
                        .findFirst()
                        .ifPresent(books::add);
            }
        }

        model.addAttribute("books", books);
        return "listBooks";
    }


    @GetMapping("/books/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("book", null);
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/books/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {

        bookService.saveBook(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @GetMapping("/books/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.listAll().stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (book == null) {
            return "redirect:/books?error=BookNotFound";
        }

        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.editBook(id, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}