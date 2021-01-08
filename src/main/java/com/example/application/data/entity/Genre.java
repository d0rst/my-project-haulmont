package com.example.application.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "GENRES")
public class Genre{
    @Id
    @GeneratedValue
    @Column(name = "GENRE_ID")
    private Long genreId;

    @Column(name = "NAME")
    private String name;
//    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
//    private Set<Book> books;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
//    public Set<Book> getBooks() {
//        return books;
//    }
//    public void setBooks(Set<Book> books) {
//        this.books = books;
//    }


    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}
