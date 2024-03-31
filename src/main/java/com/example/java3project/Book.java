package com.example.java3project;

import com.example.java3project.Author;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Book. It has an isbn, a title, an edition number, a copy right, and a list of authors.
 */

@Entity(name = "titles")
public class Book {

    @Id
    private String isbn;
    private String title;

    private int editionNumber;
    private String copyright;

    @ManyToMany
    @JoinTable(
                name = "author_isbn",
                joinColumns = @JoinColumn(name = "isbn"),
                inverseJoinColumns = @JoinColumn(name = "id")
        )
    private List<Author> authorList = new ArrayList<>();

    /**
     * Constructor for Book
     * @param isbn the isbn of the book
     * @param title the title of the book
     * @param editionNumber the edition number of the book
     * @param copyright the copy right
     * @param authorList the list of authors of the book
     */


    /**
     * Getter for isbn
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Setter for isbn
     * @param isbn the isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Getter for title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for editionNumber
     * @return the editionNumber
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * Setter for editionNumber
     * @param editionNumber the editionNumber
     */
    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * Getter for copyRight
     * @return the copyRight year of the book
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Setter for copyRight
     * @param copyright the copyRight year of the book
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Getter for authorList
     * @return the authorList
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Setter for authorList
     * @param authorList the authorList
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    /**
     * This method returns a string of the author list
     * @return a string of the author list
     */
//    public String getAuthorListString() {
//        String authorListString = "";
//        for (Author author : authorList) {
//            authorListString += author.getFirstName() + " " + author.getLastname() + ", ";
//        }
//        return authorListString;
//    }

    /**
     * This method returns a string representation of the Book including its list of authors
     * @return
     */
    @Override
    public String toString() {
        return "\n\nisbn:" + isbn + "\ntitle:" + title + "\neditionNumber:" + editionNumber + "\ncopyright:" + copyright
                //+
//                "\nauthorList:"
//                + getAuthorListString()
                ;
    }

}

