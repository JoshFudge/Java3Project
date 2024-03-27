package com.example.java3project;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author findAuthorByAuthorId(int authorId);

}
