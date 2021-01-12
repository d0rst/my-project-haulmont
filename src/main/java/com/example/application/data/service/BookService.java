package com.example.application.data.service;

import com.example.application.data.entity.Author;
import com.example.application.data.entity.Book;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);

    List<Book> getAllBooks();

    boolean delete(Long id);

    boolean delete(Book book);

    List<Book> findAll(String filterText);
}

