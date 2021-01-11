package com.example.application.data.service.impl;


import com.example.application.data.entity.Book;
import com.example.application.data.entity.Genre;
import com.example.application.data.repository.BookRepository;
import com.example.application.data.repository.GenreRepository;
import com.example.application.data.service.GenreService;
import com.vaadin.flow.component.html.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.read(Genre.class, id);
    }


    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.getAllGenres();
    }

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

    @Override
    public int getCountById(Genre genre) {
        Set<Book> books = bookRepository.getBooksByGenre(genre);
        return books.size();
    }

}
