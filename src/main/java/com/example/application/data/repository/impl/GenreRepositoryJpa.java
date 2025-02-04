package com.example.application.data.repository.impl;

import com.example.application.data.entity.Genre;
import com.example.application.data.repository.CrudRepository;
import com.example.application.data.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class GenreRepositoryJpa implements GenreRepository {

    @Autowired
    private CrudRepository<Genre> crudRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Genre genre) {
        crudRepository.create(genre);
    }

    @Override
    public Genre read(Class<Genre> clazz, Long id) {
        return crudRepository.read(clazz, id);
    }

    @Override
    public void update(Genre genre) {
        crudRepository.update(genre);
    }

    @Override
    public void delete(Genre genre) {
        crudRepository.delete(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> typedQuery = em.createQuery("select g from Genre g", Genre.class);
        return typedQuery.getResultList();
    }
}
