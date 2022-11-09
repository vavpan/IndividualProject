package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.model.PersistenceClass;

import java.util.List;

/**
 * Repository class to apply CRUD methods
 * @param <T>
 */
public interface Repository<T extends PersistenceClass> {


    int create(T t);

    T read(int id);

    List<T> read();

    boolean delete(int id);

}
