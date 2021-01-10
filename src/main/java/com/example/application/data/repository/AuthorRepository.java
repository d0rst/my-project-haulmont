package com.example.application.data.repository;

import com.example.application.data.entity.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author> {


    List<Author> getAllAuthors();

}
