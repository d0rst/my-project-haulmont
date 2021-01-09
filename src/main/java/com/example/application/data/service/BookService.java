package com.example.application.data.service;

import com.example.application.data.entity.Author;
import com.example.application.data.entity.Book;

import java.util.List;
import java.util.Set;

public interface BookService {
    Book getBookById(Long id);

    List<Book> getAllBooks();

//    Set<Book> getBooksByAuthor(Author author);
//
//    Book addBook(String name, int year, int pageCount, String isbn);
//
//    boolean delete(Long id);
//
//    boolean delete(Book book);
//
//    void addReviewToBook(String reviewerName, String text, Long bookUuid);
//
//    void delReviewFromBook(Long reviewUuid, Long bookUuid);
//
//    void addGenreToBook(Long genreUuid, Long bookUuid);
}

