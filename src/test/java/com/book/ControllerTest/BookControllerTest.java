package com.book.ControllerTest;

import com.book.Controller.BooksController;
import com.book.Model.BookDTO;
import com.book.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BooksControllerTest {

    @InjectMocks
    private BooksController booksController;

    @Mock
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn(123456789L);
        bookDTO.setAuthor("Test Author");
        bookDTO.setPublicationYear(2021);
        bookDTO.setTitle("Test Book");

        when(bookService.addBook(any(BookDTO.class))).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = booksController.addBook(bookDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertEquals(bookDTO, response.getBody());
    }

    @Test
    void testUpdateBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn(123456789L);
        bookDTO.setAuthor("Test Author 1");
        bookDTO.setPublicationYear(2021);
        bookDTO.setTitle("Test Book");

        when(bookService.updateBook(eq(123456789L), any(BookDTO.class))).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = booksController.updateBook(123456789L, bookDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals(bookDTO, response.getBody());
    }

    @Test
    void testGetAllBooks() {
        List<BookDTO> bookList = new ArrayList<>();
        BookDTO book1 = new BookDTO();
        book1.setIsbn(123456789L);
        book1.setAuthor("Author 1");
        book1.setPublicationYear(2021);
        book1.setTitle("Book 1");

        BookDTO book2 = new BookDTO();
        book2.setIsbn(987654321L);
        book2.setAuthor("Author 2");
        book2.setPublicationYear(2018);
        book2.setTitle("Book 2");

        bookList.add(book1);
        bookList.add(book2);

        when(bookService.getAllBooks()).thenReturn(bookList);

        ResponseEntity<List<BookDTO>> response = booksController.getAllBooks();

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals(bookList, response.getBody());
    }

    @Test
    void testGetBookByIsbn() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn(123456789L);
        bookDTO.setAuthor("Test Author");
        bookDTO.setPublicationYear(2021);
        bookDTO.setTitle("Test Book");

        when(bookService.getBookByIsbn(123456789L)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = booksController.getBookByIsbn(123456789L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals(bookDTO, response.getBody());
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookService).deleteBook(123456789L);

        ResponseEntity<String> response = booksController.deleteBook(123456789L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals("Book deleted successfully", response.getBody());
        verify(bookService, times(1)).deleteBook(123456789L);
    }
}
