package com.example.application.data.service.impl;

import com.example.application.data.entity.Author;
import com.example.application.data.repository.AuthorRepository;
import com.example.application.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.read(Author.class, id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    @Override
    public boolean delete(Long id) {
        Author author = authorRepository.read(Author.class, id);
        return delete(author);
    }

    @Override
    public boolean delete(Author author) {
        try {
            authorRepository.delete(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Author> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return authorRepository.getAllAuthors();
        } else  {
            return authorRepository.search(filterText);
        }
    }

}
