package com.zy.service;

import com.zy.dao.QueryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/3/12.
 */
@NoRepositoryBean
public interface IBaseService<T, ID extends Serializable> {

    Optional<T> findById(ID id);

    T add(T entity);

    T update(T entity);

    void delete(T entity);

    void delete(ID... ids);

    Page<T> findAll(int page, int size);

    Page<T> findAll(int page, int size, Sort.Direction direction, String sortField);

    List<T> findByFieldsAndValues(Object... fieldsAndValues);

    Page<T> findByFieldsAndValues(int page, int size, Object... fieldsAndValues);

    Page<T> findByFieldsAndValues(int page, int size, Sort.Direction direction, String sortField, Object... fieldsAndValues);

    List<T> findByQueryItems(List<QueryItem> queryItems);

    Page<T> findByQueryItems(int page, int size, List<QueryItem> queryItems);

    Page<T> findByQueryItems(int page, int size, Sort.Direction direction, String sortField, List<QueryItem> queryItems);
}