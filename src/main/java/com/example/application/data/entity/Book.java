package com.example.application.data.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BOOKS")
public class Book{
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "PUBLISHER")
    private String publisher;
    @Column(name = "YEAR")
    private int year;
    @Column(name = "CITY")
    private String city;
//    @Column(name = "AUTHOR_ID")
//    private Long authorId;
//    @Column(name = "GENRE_ID")
//    private Long genreId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GENRE_BOOK",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID"))
    Set<Genre> genres;

    public Book(){}

//    public String getGenreId() {
//        return String.valueOf(genreId);
//    }
//
//    public void setGenreId(Long genreId) {
//        this.genreId = genreId;
//    }
//
//    public void setGenreId(String genreId) {
//        this.genreId = Long.valueOf(genreId);
//    }
//
//    public String getAuthorId() {
//        return String.valueOf(authorId);
//    }

//    public void setAuthorId(String authorId) {
//        this.authorId = Long.valueOf(authorId);
//    }

//    public void setAuthorId(Long authorId) {
//        this.authorId = authorId;
//    }

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
        return id;
    }

    public void setBookId(Long id) {
        this.id = id;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
