package com.example.application.data.test_serv;

import com.example.application.data.entity.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;


@Service
public class test__AuthorService extends CrudService<Author, Integer> {

    private test_AuthorRepository repository;

    public test__AuthorService(@Autowired test_AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    protected test_AuthorRepository getRepository() {
        return repository;
    }

}