package com.example.application.data.service.impl;

import com.example.application.data.entity.Author;
import com.example.application.data.entity.Book;
import com.example.application.data.repository.BookRepository;
import com.example.application.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(Long id) {
        return bookRepository.read(Book.class, id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public boolean delete(Long id) {
        Book book = bookRepository.read(Book.class, id);
        return delete(book);
    }

    @Override
    public boolean delete(Book book) {
        try {
            bookRepository.delete(book);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Book> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return bookRepository.getAllBooks();
        } else  {
            return bookRepository.search(filterText);
        }
    }

}
