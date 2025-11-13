package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class BookReservationController {

    private final BookService bookService;
    private final BookReservationService reservationService;

    public BookReservationController(BookService bookService, BookReservationService reservationService) {
        this.bookService = bookService;
        this.reservationService = reservationService;
    }

    @PostMapping("/bookReservation")
    public String makeReservation(@RequestParam String bookTitle,
                                  @RequestParam String readerName,
                                  @RequestParam String readerAddress,
                                  @RequestParam int numCopies,
                                  HttpSession session) {

        Book book = bookService.listAll().stream()
                .filter(b -> b.getTitle().equals(bookTitle))
                .findFirst()
                .orElse(null);

        if (book == null)
            return "redirect:/books?error=BookNotFound";

        BookReservation reservation = reservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies, "local");

        //it tries to get a list from the session
        //akoe e prva rez, kreira prazna lista.
        @SuppressWarnings("unchecked")
        List<Long> lastReservedIds = (List<Long>) session.getAttribute("lastReservedIds");
        if (lastReservedIds == null)
            lastReservedIds = new LinkedList<>();

        //updejtira lista
        lastReservedIds.remove(book.getId());
        lastReservedIds.add(book.getId());
        //brisheme najprvata najstarata
        if (lastReservedIds.size() > 3)
            lastReservedIds.remove(0);
        //SEJVAME U LAST SESH
        session.setAttribute("lastReservedIds", lastReservedIds);
        session.setAttribute("lastReservation", reservation);

        return "redirect:/reservationConfirmation";
    }

    //renderira str otkoa ke redirektneme
    @GetMapping("/reservationConfirmation")
    public String showConfirmation(HttpSession session, Model model) {
        BookReservation reservation = (BookReservation) session.getAttribute("lastReservation");
        model.addAttribute("reservation", reservation);
        return "reservationConfirmation";
    }
}