package com.example.java3project;


import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents the main controller for the project.
 *
 * It contains the endpoints for the project.
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class MainController {



    ///////////////////////////////////////////////////////// BOOKS

    /**
     * The path for the books endpoint.
     */
    public static final String BOOK = "/books";

    /**
     * The repository for books.
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * This method returns all the books.
     * @return all the books
     */
    @GetMapping(path=BOOK)
    public @ResponseBody
    Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * This method returns a book by its isbn.
     * @param isbn the isbn
     * @return the book
     */
    @GetMapping(path=BOOK + "/{isbn}")
    public @ResponseBody
    Book getBookById(@PathVariable String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

    /**
     * This method adds a new book.
     * @param isbn the isbn
     * @param title the title
     * @param editionNumber the edition number
     * @param copyright the copy right
     * @param authorId the author id
     * @return a string
     */
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

    /**
     * This method updates a book.
     * @param isbn the isbn
     * @param title the title
     * @param edition_number the edition number
     * @param copyright the copy right
     * @param id the author id
     * @return a string
     */
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

    /**
     * This method deletes a book.
     * @param isbn the isbn
     * @return a string
     */
    @DeleteMapping(path=BOOK + "/{isbn}")
    public @ResponseBody
    String deleteBook(@PathVariable String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);
        bookRepository.delete(book);
        return "Deleted";
    }


/////////////////////////////////////////////////////AUTHORS

    /**
     * The path for the authors endpoint.
     */
    public static final String AUTHOR = "/authors";

    /**
     * The repository for authors.
     */
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * This method returns all the authors.
     * @return all the authors
     */
    @GetMapping(path=AUTHOR)
    public @ResponseBody
    Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * This method returns an author by their authorId.
     * @param authorId the authorId
     * @return the author
     */
    @GetMapping(path=AUTHOR + "/{authorId}")
    public @ResponseBody
    Author getAuthorById(@PathVariable int authorId) {
        return authorRepository.findAuthorByAuthorId(authorId);
    }


    /**
     * This method adds a new author.
     * @param firstName the first name
     * @param lastName the last name
     * @return a string
     */
    @PostMapping(path=AUTHOR)
    public @ResponseBody
    String addNewAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastname(lastName);
        authorRepository.save(author);
        return "Saved";
    }


    /**
     * This method updates an author.
     * @param authorId the authorId
     * @param firstName the first name
     * @param lastName the last name
     * @return a string
     */
    @PutMapping(path=AUTHOR + "/{authorId}")
    public @ResponseBody
    String updateAuthor(@PathVariable int authorId, @RequestParam String firstName, @RequestParam String lastName) {

        Author author = authorRepository.findAuthorByAuthorId(authorId);

        author.setFirstName(firstName);
        author.setLastname(lastName);
        authorRepository.save(author);
        return "Updated";
    }


/**
     * This method deletes an author.
     * @param authorId the authorId
     * @return a string
     */
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
