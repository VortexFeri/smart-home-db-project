package com.iec3.smarthome.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T,k> {

    int insert(T t);
    int delete(k id);
    int update(k id, T t);
    List<T> getAll();
    Optional<T> getById(k id);

}
