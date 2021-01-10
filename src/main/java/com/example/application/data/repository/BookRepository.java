package com.example.application.data.repository;


import com.example.application.data.entity.Book;

import java.util.List;


public interface BookRepository extends CrudRepository<Book> {

    List<Book> getAllBooks();

}
