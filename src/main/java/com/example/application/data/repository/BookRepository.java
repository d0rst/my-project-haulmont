package com.example.application.data.repository;


import com.example.application.data.entity.Book;
import com.example.application.data.entity.Genre;

import java.util.List;
import java.util.Set;


public interface BookRepository extends CrudRepository<Book> {
    Set<Book> getBooksByGenre(Genre genre);
    List<Book> getAllBooks();
}
