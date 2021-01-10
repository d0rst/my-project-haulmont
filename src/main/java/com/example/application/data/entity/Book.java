package com.example.application.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class Book{
    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long bookId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "PUBLISHER")
    private String publisher;
    @Column(name = "YEAR")
    private int year;
    @Column(name = "CITY")
    private String city;
    @Column(name = "AUTHOR_ID")
    private Long authorId;
    @Column(name = "GENRE_ID")
    private Long genreId;

    public Book(){}

    public String getGenreId() {
        return String.valueOf(genreId);
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = Long.valueOf(genreId);
    }

    public String getAuthorId() {
        return String.valueOf(authorId);
    }

    public void setAuthorId(String authorId) {
        this.authorId = Long.valueOf(authorId);
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
