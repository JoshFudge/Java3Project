package com.example.java3project;



import java.util.List;

/**
 * This class represents an Author of a book. It has an authorId, a first name, a last name, and a list of books.
 */
public class Author {

    private int authorId;
    private String firstName;
    private String lastname;
    private List<Book> bookList;

    /**
     * Constructor for Author
     * @param authorId the id of the author
     * @param firstName the first name of the author
     * @param lastname the last name of the author
     * @param bookList the list of books the author has written
     */
    public Author(int authorId, String firstName, String lastname
            , List<Book> bookList
    ) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastname = lastname;
        this.bookList = bookList;
    }

    /**
     * Getter for authorId
     * @return the authorId
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Setter for authorId
     * @param authorId the authorId
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }


    /**
     * Getter for firstName
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName
     * @param firstName the firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastname
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Setter for lastname
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Getter for bookList
     * @return the bookList
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Setter for bookList
     * @param bookList the bookList
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }


    /**
     * This method returns a string of the books the author has written
     * @return a string of the books the author has written
     */
    public String getBookListString() {
        String bookListString = "";
        for (Book book : bookList) {
            bookListString += book.getTitle() + ", ";
        }
        return bookListString;
    }

    /**
     * This method returns a string representation of the Author
     * @return a string representation of the Author
     */
    @Override
    public String toString() {
        return "\n\nauthorId:" + authorId + "\nfirstName:" + firstName + "\nlastname:" + lastname  + "\nbookList:" + getBookListString();
    }
}
