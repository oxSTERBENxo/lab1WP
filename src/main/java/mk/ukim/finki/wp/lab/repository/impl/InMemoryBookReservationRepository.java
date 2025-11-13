package mk.ukim.finki.wp.lab.repository.impl;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {

    private final List<BookReservation> reservations = new ArrayList<>();

    public List<BookReservation> findAll() {
        return reservations;
    }

    @Override
    public BookReservation save(BookReservation reservation) {
        DataHolder.reservations.add(reservation);
        return reservation;
    }
}
