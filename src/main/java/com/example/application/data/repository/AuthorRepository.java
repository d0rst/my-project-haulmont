package com.example.application.data.repository;

import com.example.application.data.entity.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author> {

//    Set<Book> getBooksByAuthor(Author author);

//    Set<Book> getBooksByAuthor(UUID uuid);

    List<Author> getAllAuthors();

}
