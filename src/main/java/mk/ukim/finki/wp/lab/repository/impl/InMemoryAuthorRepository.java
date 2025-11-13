package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryAuthorRepository implements AuthorRepository {

    private final List<Author> authors = new ArrayList<>();

    public InMemoryAuthorRepository() {
        authors.add(new Author("John", "Doe", "USA", "Author of mystery novels."));
        authors.add(new Author("Maria", "Garcia", "Spain", "Writes historical fiction."));
        authors.add(new Author("Liam", "Smith", "UK", "Known for science-fiction stories."));
    }

    @Override
    public List<Author> findAll() {
        return authors;
    }
}
