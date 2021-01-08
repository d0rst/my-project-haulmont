package com.example.application.data.service;

import com.example.application.data.entity.Author;
import com.example.application.data.repository.AuthorRepository;
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

//    @Override
//    public int getAuthorCount() {
//       return authorRepository.getAuthorCount();
//    }

//    @Override
//    public Set<Book> getBooksByAuthor(Author author) {
//        return authorRepository.getBooksByAuthor(author);
//    }

//    @Override
//    public Set<Book> getBooksByAuthor(UUID uuid) {
//        return authorRepository.getBooksByAuthor(uuid);
//    }

//    @Override
//    public Author addAuthor(String name) {
//        try {
//            Author author = new Author(name);
//            authorRepository.create(author);
//            return author;
//        } catch (Exception e) {
//            log.error("Error adding author", e);
//        }
//        return null;
//    }

//    @Override
//    public boolean delete(Long id) {
//        Author author = authorRepository.read(Author.class, id);
//        return delete(author);
//    }

//    @Override
//    public boolean delete(Author author) {
//        try {
//            authorRepository.delete(author);
//        } catch (Exception e) {
//            log.error("Error deleting author - " + author.getId(), e);
//            return false;
//        }
//        return true;
//    }

}
