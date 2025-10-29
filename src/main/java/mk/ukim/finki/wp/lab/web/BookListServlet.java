package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListServlet", urlPatterns = "")
public class BookListServlet extends HttpServlet {

    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookListServlet(BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // bildanje tajmlif bro
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String text = req.getParameter("text");
        String ratingStr = req.getParameter("rating");

        List<Book> books;
        //ako ima search filter logika
        if ((text != null && !text.isEmpty()) || (ratingStr != null && !ratingStr.isEmpty())) {
            double rating = (ratingStr == null || ratingStr.isEmpty())
                    ? 0.0
                    : Double.parseDouble(ratingStr);
            books = bookService.searchBooks(text == null ? "" : text, rating);
        } else {
            books = bookService.listAll();
        }

        context.setVariable("books", books);

        springTemplateEngine.process("listBooks.html", context, resp.getWriter());

    }
}
