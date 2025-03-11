package io.github.cursodsousa.libraryapi;

import io.github.cursodsousa.libraryapi.model.Author;
import io.github.cursodsousa.libraryapi.repository.AuthorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class LibraryapiApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(LibraryapiApplication.class, args);
		AuthorRepository authorRepository = context.getBean(AuthorRepository.class);

		exampleSaveRegister(authorRepository);
	}

	public static void exampleSaveRegister(AuthorRepository authorRepository) {
		Author author= new  Author();
		author.setName("Rodrigo Turini");
		author.setNationality("Brazilian");
		author.setBirthDate(LocalDate.of(1980, 1, 1));

		var authorSaved = authorRepository.save(author);
		System.out.printf("Author saved: %s", authorSaved);
	}
}
