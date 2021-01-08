package com.example.application.data.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CrudRepositoryImpl<T> implements CrudRepository<T> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(T t) {
        em.persist(t);
    }

    @Override
    public T read(Class<T> clazz, Long uuid) {
        return em.find(clazz, uuid);
    }

    @Override
    public void update(T t) {
        em.merge(t);
    }

    @Override
    public void delete(T t) {
        if (em.contains(t)) {
            em.remove(t);
        } else {
            em.remove(em.merge(t));
        }
    }
}
