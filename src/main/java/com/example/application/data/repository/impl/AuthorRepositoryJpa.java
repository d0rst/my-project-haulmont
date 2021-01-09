package com.example.application.data.repository.impl;

import com.example.application.data.entity.Author;
import com.example.application.data.repository.AuthorRepository;
import com.example.application.data.repository.CrudRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@Transactional
public class AuthorRepositoryJpa implements AuthorRepository {

    @Autowired
    private CrudRepository<Author> crudRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Author author) {
        crudRepository.create(author);
    }

    @Override
    public Author read(Class<Author> clazz, Long uuid) {
        return crudRepository.read(clazz, uuid);
    }

    @Override
    public void update(Author author) {
        crudRepository.update(author);
    }

    @Override
    public void delete(Author author) {
        crudRepository.delete(author);
    }

//    @Override
//    public Set<Book> getBooksByAuthor(Author author) {
//
//        TypedQuery<Book> typedQuery = em.createQuery("SELECT b FROM Book b   WHERE :author member of b.authors", Book.class);
//        typedQuery.setParameter("author", author);
//        return typedQuery.getResultStream().collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<Book> getBooksByAuthor(UUID uuid) {
//
//        TypedQuery<Book> typedQuery = em.createQuery("SELECT b FROM Book b   WHERE :author member of b.authors", Book.class);
//        typedQuery.setParameter("author", uuid);
//        return typedQuery.getResultStream().collect(Collectors.toSet());
//    }

    //TODO тут sql запрос говна
    @Override
    public List<Author> getAllAuthors() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

//    @Override
//    public int getAuthorCount() {
//        return 4;
//    }
}
