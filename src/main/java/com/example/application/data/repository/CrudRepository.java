package com.example.application.data.repository;

public interface CrudRepository<T> {

    void create(T t);

    T read(Class<T> clazz, Long uuid);

    void update(T t);

    void delete(T t);
}
