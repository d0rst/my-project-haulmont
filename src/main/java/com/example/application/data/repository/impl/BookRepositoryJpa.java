package com.example.application.data.repository.impl;

import com.example.application.data.entity.Author;
import com.example.application.data.entity.Book;
import com.example.application.data.repository.BookRepository;
import com.example.application.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
public class BookRepositoryJpa implements BookRepository {
    private CrudRepository<Book> crudRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Book book) {
        crudRepository.create(book);
    }

    @Override
    public Book read(Class<Book> clazz, Long id) {
        return crudRepository.read(clazz, id);
    }

    @Override
    public void update(Book book) {
        crudRepository.update(book);
    }

    @Override
    public void delete(Book book) {
        crudRepository.delete(book);
    }


    //TODO тут sql запрос говна
    @Override
    public Set<Book> getBooksByAuthor(Author author) {
        TypedQuery<Book> typedQuery =
                em.createQuery("select b from Book b  where  :author MEMBER of b.authors", Book.class);
        typedQuery.setParameter("author", author);
        return typedQuery.getResultStream()
                .collect(Collectors.toSet());
    }

    //TODO тут sql запрос говна
    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }


}
