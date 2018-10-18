package com.mdevi.webcrud.controller;

import com.mdevi.webcrud.model.Book;
import com.mdevi.webcrud.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public String showPersons(Model model, @RequestParam(defaultValue = "0") int pageIndex) {
        Page<Book> bookPage = bookRepository.findAll(PageRequest.of(pageIndex, 10));
        model.addAttribute("data", bookPage);
        model.addAttribute("currentPage", pageIndex);
        return "books";
    }


    @PostMapping("/saveBook")
    public String saveBook(Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

/*    // not realized yet.
    @DeleteMapping("/delete/{id}")
    public String processDelete(@PathVariable String id) {
        bookRepository.deleteById(Long.parseLong(id));
        return "redirect:/books";
    }*/

    @GetMapping("/getBook")
    @ResponseBody
    public Book getTheBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

}
