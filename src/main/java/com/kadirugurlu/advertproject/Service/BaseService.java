package com.kadirugurlu.advertproject.Service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    T create(T entity);

    Optional<T> read(Long id);

    T update(T entity);

    void delete(Long id);

    List<T> getAll();
}
