package com.example.java3project;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * This interface represents a repository for books.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {

    /**
     * This method finds a book by its isbn.
     * @param isbn the isbn
     * @return the book
     */
    Book findBookByIsbn(String isbn);

    /**
     * This method finds a list of books by their AuthorList.
     * @return the list of books
     */
    List<Book> findBooksByAuthorListContaining(Author author);


}
