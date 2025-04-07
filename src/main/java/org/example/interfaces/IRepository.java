package org.example.interfaces;

import java.util.List;
import java.util.Optional;

public interface IRepository <T> {
    int addNew(T t);
    List<T> getAll();
    void update(T t);
    void delete(T t);
    Optional<T> getById(int id);
}
