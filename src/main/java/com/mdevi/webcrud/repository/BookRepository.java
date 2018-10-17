package com.mdevi.webcrud.repository;

import com.mdevi.webcrud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
