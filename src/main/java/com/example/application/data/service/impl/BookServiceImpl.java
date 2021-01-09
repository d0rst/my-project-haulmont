package com.example.application.data.service.impl;

import com.example.application.data.entity.Book;
import com.example.application.data.repository.BookRepository;
import com.example.application.data.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Override
    public Book getBookById(Long id) {
        return bookRepository.read(Book.class, id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
//
//    @Override
//    public Set<Book> getBooksByAuthor(Author author) {
//        return bookRepository.getBooksByAuthor(author);
//    }
//
//    @Override
//    public Book addBook(String name, int year, int pageCount, String isbn) {
//        try {
//            Book book = new Book(name, year, pageCount, isbn);
//            bookRepository.create(book);
//            return book;
//        } catch (Exception e) {
//            log.error("Error adding book", e);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean delete(UUID id) {
//        Book book = bookRepository.read(Book.class, id);
//        return delete(book);
//    }
//
//    @Override
//    public boolean delete(Book book) {
//        try {
//            bookRepository.delete(book);
//        } catch (Exception e) {
//            log.error("Error deleting book - " + book.getId(), e);
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    @Transactional
//    public void addReviewToBook(String reviewerName, String text, UUID bookUuid) {
//        Review review = new Review(reviewerName, LocalDateTime.now(), text);
//        reviewRepository.create(review);
//        Book book = bookRepository.read(Book.class, bookUuid);
//        book.addReview(review);
//        bookRepository.update(book);
//    }

//    @Override
//    @Transactional
//    public void delReviewFromBook(UUID reviewUuid, UUID bookUuid) {
//        Review review = reviewRepository.read(Review.class, reviewUuid);
//        Book book = bookRepository.read(Book.class, bookUuid);
//        book.delReview(review);
//        bookRepository.update(book);
//    }
//
//    @Override
//    public void addGenreToBook(UUID genreUuid, UUID bookUuid) {
//        Genre genre = genreRepository.read(Genre.class, genreUuid);
//        Book book = bookRepository.read(Book.class, bookUuid);
//        book.delGenre(genre);
//        bookRepository.update(book);
//    }


}
