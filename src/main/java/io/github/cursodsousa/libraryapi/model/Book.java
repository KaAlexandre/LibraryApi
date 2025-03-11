package io.github.cursodsousa.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "book", schema = "public")
@Data
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Column(name = "publication_date")
    private LocalDate publishedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 30 , nullable = false)
    private BookGenre genre;

    @Column(name = "price",precision = 18, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne // muitos livros para um autor
    @JoinColumn(name = "author_id")
    private Author author;
}
