package com.example.application.data.service;

import com.example.application.data.entity.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthorById(Long id);

    List<Author> getAllAuthors();


//    Set<Book> getBooksByAuthor(Author author);

//    Set<Book> getBooksByAuthor(UUID uuid);

//    Author addAuthor(String name, LocalDate birth);

//    boolean delete(UUID id);

//    boolean delete(Author author);
}
