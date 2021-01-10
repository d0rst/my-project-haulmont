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

    public Genre(){}

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}
