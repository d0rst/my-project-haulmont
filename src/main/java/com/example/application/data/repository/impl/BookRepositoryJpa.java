package com.example.application.data.repository.impl;

import com.example.application.data.entity.Author;
import com.example.application.data.entity.Book;
import com.example.application.data.entity.Genre;
import com.example.application.data.repository.BookRepository;
import com.example.application.data.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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

    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Set<Book> getBooksByGenre(Genre genre) {
        TypedQuery<Book> typedQuery =
                em.createQuery("select b from Book b  where  :genre member of b.genres", Book.class);
        typedQuery.setParameter("genre", genre);
        return typedQuery.getResultStream()
                .collect(Collectors.toSet());
    }

    public List<Book> search(String searchTerm) {
        TypedQuery<Book> typedQuery = em.createQuery(
                "select DISTINCT b from Book b " +
                        "join FETCH b.genres g " +
                        "join FETCH b.authors a " +
                        "where lower(g.name) = lower(:searchTerm) " +
                        "or lower(a.lastName) = lower(:searchTerm) " +
                        "or lower(b.title) like lower(concat('%', :searchTerm, '%')) " +
                        "or lower(b.publisher) like lower(concat('%', :searchTerm, '%')) "
                , Book.class);
        typedQuery.setParameter("searchTerm", searchTerm);
        return typedQuery.getResultList();
    }
}
