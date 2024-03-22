package com.example.java3project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    public static final String BOOK = "/books";
    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path=BOOK)
    public @ResponseBody
    Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(path=BOOK + "/{isbn}")
    public @ResponseBody
    Book getBookById(@PathVariable String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }
}
