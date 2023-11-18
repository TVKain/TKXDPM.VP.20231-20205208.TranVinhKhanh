package com.hust.aims.service.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    T get(Integer id);

    boolean update(T item);
    boolean delete(T item);
}

