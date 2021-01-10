package com.example.application.data.service;

import com.example.application.data.entity.Genre;

import java.util.List;

public interface GenreService {
    Genre getGenreById(Long id);

//    Set<Genre> getGenresByBook(Long bookUuid);
    List<Genre> getAllGenres();
    boolean delete(Long id);
    boolean delete(Genre genre);
}
