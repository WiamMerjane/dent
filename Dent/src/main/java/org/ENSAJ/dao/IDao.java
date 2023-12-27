package org.ENSAJ.dao;

import java.util.List;

public interface IDao <T>{
    T create(T o);
    T update(T o);
    T findById(int id);
    List<T> findAll();
    boolean delete(T o);
}
