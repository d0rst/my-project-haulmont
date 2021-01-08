package com.example.application.data.test_serv;

import com.example.application.data.entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;


public interface test_AuthorRepository extends JpaRepository<Author, Integer> {
}