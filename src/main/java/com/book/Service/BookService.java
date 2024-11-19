package com.book.Service;

import com.book.Model.BookDTO;
import com.book.Model.Books;
import com.book.Repository.BookRepo;
import com.book.Exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepository;

    public BookDTO addBook(BookDTO bookDTO) {
        Books book = convertToEntity(bookDTO);
        Books savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    public BookDTO updateBook(Long isbn, BookDTO bookDTO) { 
        Books existingBook = bookRepository.findByIsbn(isbn);
        if (existingBook == null) {
            throw new BookNotFoundException("Book not found with ISBN: " + isbn);
        }
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setPublicationYear(bookDTO.getPublicationYear());
        existingBook.setTitle(bookDTO.getTitle());
        Books updatedBook = bookRepository.save(existingBook);
        return convertToDTO(updatedBook);
    }

    public void deleteBook(Long isbn) {
        Books book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ISBN: " + isbn);
        }
        bookRepository.delete(book);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookByIsbn(Long isbn) {
        Books book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ISBN: " + isbn);
        }
        return convertToDTO(book);
    }

    private BookDTO convertToDTO(Books book) {
        return new BookDTO(book.getIsbn(), book.getAuthor(), book.getPublicationYear(), book.getTitle());
    }

    private Books convertToEntity(BookDTO bookDTO) {
        return new Books(bookDTO.getIsbn(), bookDTO.getAuthor(),  bookDTO.getPublicationYear(), bookDTO.getTitle());
    }
}
