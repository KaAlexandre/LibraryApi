package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Author;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTests {
    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void saveTest() {
        Author author = new Author();
        author.setName("Flavia Mantreal");
        author.setNationality("Brazilian");
        author.setBirthDate(LocalDate.of(2004, 11, 3));

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

    @Test
    public void listTest() {
        List<Author> authorsList = authorRepository.findAll();
        authorsList.forEach(System.out::println);
    }
    @Test
    public void countTest() {
        System.out.println("Authors count: " + authorRepository.count());
    }
    @Test
    public void  deleteByIdTest(){
        var id = UUID.fromString("dc0c9375-46ba-45a6-b0ab-b8cf449e0c46");
        authorRepository.deleteById(id);
    }
    @Test
    public void deleteTest(){
        var id = UUID.fromString("5d3530c6-0006-40b7-9770-9cf78a78082d");
        var flavia = authorRepository.findById(id).get();
        authorRepository.delete(flavia);
    }
}