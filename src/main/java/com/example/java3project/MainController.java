package com.example.java3project;


import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class MainController {



    ///////////////////////////////////////////////////////// BOOKS
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

    @PostMapping(path=BOOK)
    public @ResponseBody
    String addNewBook(@RequestParam String isbn, @RequestParam String title, @RequestParam int editionNumber, @RequestParam String copyright, @RequestParam int authorId) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setCopyright(copyright);
        book.setEditionNumber(editionNumber);
        Author author = authorRepository.findAuthorByAuthorId(authorId);
        if (author != null) {
            List<Author> authorList = book.getAuthorList();
            authorList.add(author);
            book.setAuthorList(authorList);
        }
        bookRepository.save(book);
        return "Saved";
    }

    @PutMapping(path=BOOK + "/{isbn}")
    public @ResponseBody
    String updateBook(@PathVariable String isbn, @RequestParam String title, @RequestParam int edition_number, @RequestParam String copyright, @RequestParam int id) {
        Book book = bookRepository.findBookByIsbn(isbn);
        book.setTitle(title);
        book.setEditionNumber(edition_number);
        book.setCopyright(copyright);
        Author author = authorRepository.findAuthorByAuthorId(id);
        if (author != null) {
            List<Author> authorList = book.getAuthorList();
            if (!authorList.contains(author)) {
                authorList.add(author);
                book.setAuthorList(authorList);
            }

        }
        bookRepository.save(book);
        return "Updated";
    }

    @DeleteMapping(path=BOOK + "/{isbn}")
    public @ResponseBody
    String deleteBook(@PathVariable String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);
        bookRepository.delete(book);
        return "Deleted";
    }


/////////////////////////////////////////////////////AUTHORS
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



    @PostMapping(path=AUTHOR)
    public @ResponseBody
    String addNewAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastname(lastName);
        authorRepository.save(author);
        return "Saved";
    }


    @PutMapping(path=AUTHOR + "/{authorId}")
    public @ResponseBody
    String updateAuthor(@PathVariable int authorId, @RequestParam String firstName, @RequestParam String lastName) {



        Author author = authorRepository.findAuthorByAuthorId(authorId);



        author.setFirstName(firstName);
        author.setLastname(lastName);
        authorRepository.save(author);
        return "Updated";
    }



    @DeleteMapping(path=AUTHOR + "/{authorId}")
    public @ResponseBody
    String deleteAuthor(@PathVariable int authorId) {
        Author author = authorRepository.findAuthorByAuthorId(authorId);
        List<Book> bookList = bookRepository.findBooksByAuthorListContaining(author);
        System.out.println(bookList);
        for (Book book : bookList) {
            List<Author> authorList = book.getAuthorList();
            authorList.remove(author);
            book.setAuthorList(authorList);
            bookRepository.save(book);
        }
        authorRepository.delete(author);
        return "Deleted";
    }

}
