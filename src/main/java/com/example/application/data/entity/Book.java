package com.example.application.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class Book{
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "AUTHOR_BOOK",
//            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
//            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"))
//    Set<Author> authors;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "GENRE_BOOK",
//            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
//            inverseJoinColumns = @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID"))
//    Set<Genre> genres;

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "TITLE")
    private String title;
    @Enumerated(EnumType.STRING)
    private Publisher publisher;
    @Column(name = "YEAR")
    private int year;
    @Column(name = "CITY")
    private String city;
    @Column(name = "AUTHOR_ID")
    private Long authorId;
    @Column(name = "GENRE_ID")
    private Long genreId;

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public Long getAuthorId() {
        return authorId;
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
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
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
