package com.example.application.data.repository;

import com.example.application.data.entity.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre> {
//    Set<Genre> getGenresByBook(Book book);
    List<Genre> getAllGenres();

//    int getCount();
}
