package com.example.application.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORS")
public class Author {
    @Id
    @GeneratedValue
    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "PATRONYMIC")
    private String patronymic;

    public Author(){}

    public Author(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
//    public Set<Book> getBooks() {
//        return books;
//    }
//    public void setBooks(Set<Book> books) {
//        this.books = books;
//    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
