package com.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.book.Exceptions.BookNotFoundException;
import com.book.Model.BookDTO;
import com.book.Service.BookService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Validated
public class BooksController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book_addBook")
    public ResponseEntity<BookDTO> addBook(@RequestBody @Valid BookDTO bookDTO) {
        BookDTO newBook = bookService.addBook(bookDTO);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/book_updateBook/{isbn}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long isbn, @RequestBody @Valid BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(isbn, bookDTO);
        if (updatedBook == null) {
            throw new BookNotFoundException("Book not found with ISBN: " + isbn);
        }
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/book_getAllBooks")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book_getOne/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable Long isbn) {
        BookDTO book = bookService.getBookByIsbn(isbn);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ISBN: " + isbn);
        }
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/book_deleteBook/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable Long isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
