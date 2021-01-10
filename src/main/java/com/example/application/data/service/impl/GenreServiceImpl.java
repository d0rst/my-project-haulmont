package com.example.application.data.service.impl;


import com.example.application.data.entity.Genre;
import com.example.application.data.repository.BookRepository;
import com.example.application.data.repository.GenreRepository;
import com.example.application.data.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.read(Genre.class, id);
    }


    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.getAllGenres();
    }

//    @Override
//    public Genre addGenre(String name) {
//        try {
//            Genre genre = new Genre(name);
//            genreRepository.create(genre);
//            return genre;
//        } catch (Exception e) {
//            log.error("Error adding genre", e);
//        }
//        return null;
//    }

    @Override
    public boolean delete(Long id) {
        Genre genre = genreRepository.read(Genre.class, id);
        return delete(genre);
    }

    @Override
    public boolean delete(Genre genre) {
        try {
            genreRepository.delete(genre);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
