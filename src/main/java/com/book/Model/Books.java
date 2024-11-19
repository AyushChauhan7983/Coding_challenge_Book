package com.book.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @Column(unique = true)
    private long isbn;

    @Column
    @NotBlank(message = "Title is required")
    private String title;

    @Column
    @NotBlank(message = "Author is required")
    private String author;

    @Column
    private int publicationYear;

    public Books() {}

    public Books(long isbn, String author, int publicationYear, String title) {
        this.isbn = isbn;
        this.author = author;
        this.publicationYear = publicationYear;
        this.title = title;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Books{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
