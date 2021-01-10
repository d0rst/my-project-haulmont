package com.example.application.data.service;

import com.example.application.data.entity.Book;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);

    List<Book> getAllBooks();

    boolean delete(Long id);

    boolean delete(Book book);
//
//    void addGenreToBook(Long genreUuid, Long bookUuid);
}

