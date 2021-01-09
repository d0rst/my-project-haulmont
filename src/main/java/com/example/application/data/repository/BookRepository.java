package com.example.application.data.repository;

import com.example.application.data.entity.Author;
import com.example.application.data.entity.Book;

import java.util.List;
import java.util.Set;

public interface BookRepository extends CrudRepository<Book> {

    Set<Book> getBooksByAuthor(Author author);

    List<Book> getAllBooks();

}
