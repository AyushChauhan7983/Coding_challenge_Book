package com.book.Repository;

import com.book.Model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Books, Long> {
	
    Books findByIsbn(Long isbn);
}
