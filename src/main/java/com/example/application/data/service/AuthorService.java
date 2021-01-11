package com.example.application.data.service;

import com.example.application.data.entity.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthorById(Long id);

    List<Author> getAllAuthors();

    boolean delete(Long id);

    boolean delete(Author author);

    List<Author> findAll(String filterText);
}
