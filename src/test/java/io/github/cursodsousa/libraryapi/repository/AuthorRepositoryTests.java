package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Author;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTests {
    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void saveTest() {
        Author author = new Author();
        author.setName("Rodrigo Turini");
        author.setNationality("Brazilian");
        author.setBirthDate(LocalDate.of(1980, 1, 1));

        var authorSaved = authorRepository.save(author);
        System.out.printf("Author saved: %s", authorSaved);
    }

    @Test
    public void updateTest() {
        var id = UUID.fromString("7c5c0dd0-6271-43b1-b473-9aef87409cb3");

        Optional<Author> maybeAuthor = authorRepository.findById(id);
        if (maybeAuthor.isPresent()) {
            Author foundAuthor = maybeAuthor.get();
            System.out.println("Author Data:");
            System.out.println(foundAuthor);
            foundAuthor.setNationality("Mexican");

            authorRepository.save(foundAuthor);
            System.out.println("Author updated:");
            System.out.println(foundAuthor);
        }
    }
}
