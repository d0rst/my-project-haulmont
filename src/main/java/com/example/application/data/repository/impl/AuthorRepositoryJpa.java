package com.example.application.data.repository.impl;

import com.example.application.data.entity.Author;
import com.example.application.data.repository.AuthorRepository;
import com.example.application.data.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Override
    public List<Author> getAllAuthors() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    public List<Author> search(String searchTerm) {
        TypedQuery<Author> query = em.createQuery("select a from Author a " +
                "where lower(a.firstName) like lower(concat('%', :searchTerm, '%')) " +
                "or lower(a.lastName) like lower(concat('%', :searchTerm, '%'))" +
                "or lower(a.patronymic) like lower(concat('%', :searchTerm, '%'))", Author.class);
        query.setParameter("searchTerm", searchTerm);
        return query.getResultList();
    }
}
