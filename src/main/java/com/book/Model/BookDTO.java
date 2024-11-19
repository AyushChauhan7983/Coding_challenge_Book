package com.book.Model;

import jakarta.validation.constraints.*;

public class BookDTO {

    private long isbn;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    private int publicationYear;

    public BookDTO() {}

    public BookDTO(long isbn, String author, int publicationYear, String title) {
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
}
