package com.example.java3project;

import org.springframework.data.repository.CrudRepository;

/**
 * This interface represents a repository for authors.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    /**
     * This method finds an author by their authorId.
     * @param authorId the authorId
     * @return the author
     */
    Author findAuthorByAuthorId(int authorId);

}
