package com.mdevi.webcrud.controller;

import com.mdevi.webcrud.repository.BookRepository;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


}
