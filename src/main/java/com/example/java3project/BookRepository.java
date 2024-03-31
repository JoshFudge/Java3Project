package com.example.java3project;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findBookByIsbn(String isbn);

    List<Book> findBooksByAuthorListContaining(Author author);


}
