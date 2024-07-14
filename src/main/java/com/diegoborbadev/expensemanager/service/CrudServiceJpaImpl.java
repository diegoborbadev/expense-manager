package com.diegoborbadev.expensemanager.service;

import com.diegoborbadev.expensemanager.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class CrudServiceJpaImpl<L extends JpaRepository<T, ID>, T extends BaseModel<ID>, ID> implements CrudService<T, ID> {

    @Autowired
    protected L repository;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<T> findAllById(List<ID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public T createElement(T element) {
        element.setId(null);
        return repository.save(element);
    }

    @Override
    @Transactional
    public List<T> createElements(List<T> elements) {
        return repository.saveAll(elements);
    }

    @Override
    @Transactional
    public Optional<T> updateElement(ID id, T element) {
        Optional<T> t = findById(id);
        if (t.isPresent()) {
            element.setId(t.get().getId());
            element.setVersion(t.get().getVersion());
            return Optional.of(repository.save(element));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteElement(ID id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteElement(T element) {
        boolean exists = repository.existsById(element.getId());
        if (exists) {
            repository.delete(element);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public long count() {
        return repository.count();
    }
}