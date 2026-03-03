package com.test.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T, ID extends Serializable> {
    boolean create(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    T findById(ID id);
    List<T> findAll();
}
