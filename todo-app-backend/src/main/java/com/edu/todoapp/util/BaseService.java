package com.edu.todoapp.util;

import java.util.List;
import java.util.Optional;

public interface BaseService <M,P> {

    M create(M m);
    M update(M m);
    Optional<M> findById(P id);
    void deleteById(P id);
    List<M> findAll();
}
