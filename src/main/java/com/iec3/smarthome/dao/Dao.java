package com.iec3.smarthome.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    int insert(T t);
    int delete(int id);
    int update(int id, T t);
    List<T> getAll();
    Optional<T> getById(int id);
}
