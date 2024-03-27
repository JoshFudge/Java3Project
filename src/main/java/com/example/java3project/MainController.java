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



    public static final String AUTHOR = "/authors";

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(path=AUTHOR)
    public @ResponseBody
    Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping(path=AUTHOR + "/{authorId}")
    public @ResponseBody
    Author getAuthorById(@PathVariable int authorId) {
        return authorRepository.findAuthorByAuthorId(authorId);
    }
}
