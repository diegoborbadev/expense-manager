package com.diegoborbadev.expensemanager.service;

import com.diegoborbadev.expensemanager.model.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CrudService<T extends BaseModel<ID>, ID> {
    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    List<T> findAllById(List<ID> ids);

    Optional<T> findById(ID id);

    T createElement(T element);

    List<T> createElements(List<T> elements);

    Optional<T> updateElement(ID id, T element);

    boolean deleteElement(ID id);

    boolean deleteElement(T element);

    void deleteAll();

    long count();
}