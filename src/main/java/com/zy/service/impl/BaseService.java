package com.zy.service.impl;

import com.zy.dao.BaseRepository;
import com.zy.dao.QueryItem;
import com.zy.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class  BaseService<T, ID extends Serializable> implements IBaseService<T, ID> {
    private static final String PARAMS_NUMBERS_ERROR = "参数个数必须为偶数";

    @Autowired
    BaseRepository<T, ID> baseRepository;

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(baseRepository.getOne(id));
    }

    @Override
    public T add(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public T update(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    @Override
    public void delete(ID[] ids) {
        Arrays.stream(ids).forEach(id -> {
            Optional<T> optional = findById(id);
            if (optional.isPresent()) {
                delete(optional.get());
            }
        });
    }

    @Override
    public Page<T> findAll(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        return baseRepository.findAll(pageRequest);
    }

    @Override
    public Page<T> findAll(int page, int size, Sort.Direction direction, String sortField) {
        Sort sort = new Sort(direction, sortField);
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return baseRepository.findAll(pageRequest);
    }

    @Override
    public List<T> findByFieldsAndValues(Object... fieldsAndValues) {
        return baseRepository.findAll(getWhereSpecification(fieldsAndValues));
    }

    @Override
    public Page<T> findByFieldsAndValues(int page, int size, Object... fieldsAndValues) {
        PageRequest pageRequest = new PageRequest(page, size);
        return baseRepository.findAll(getWhereSpecification(fieldsAndValues), pageRequest);
    }

    @Override
    public Page<T> findByFieldsAndValues(int page, int size, Sort.Direction direction, String sortField, Object... fieldsAndValues) {
        Sort sort = new Sort(direction, sortField);
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return baseRepository.findAll(getWhereSpecification(fieldsAndValues), pageRequest);
    }

    private Specification getWhereSpecification(final Object... fieldsAndValues) {
        this.CheckFieldsAndValues();
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Object> predicates = new ArrayList<>();
                for (int i = 0; i < fieldsAndValues.length; i += 2) {
                    Predicate predicate = cb.equal(root.get(fieldsAndValues[i].toString()).as(String.class), fieldsAndValues[i + 1]);
                    predicates.add(predicate);
                }
                query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                return query.getRestriction();
            }
        };
        return specification;
    }

    @Override
    public List<T> findByQueryItems(List<QueryItem> queryItems) {
        return baseRepository.findAll(getWhereSpecification(queryItems));
    }

    @Override
    public Page<T> findByQueryItems(int page, int size, List<QueryItem> queryItems) {
        PageRequest pageRequest = new PageRequest(page, size);
        return baseRepository.findAll(getWhereSpecification(queryItems), pageRequest);
    }

    @Override
    public Page<T> findByQueryItems(int page, int size, Sort.Direction direction, String sortField, List<QueryItem> queryItems) {
        Sort sort = new Sort(direction, sortField);
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return baseRepository.findAll(getWhereSpecification(queryItems), pageRequest);
    }

    private Specification getWhereSpecification(final List<QueryItem> queryItems) {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Object> predicates = new ArrayList<>();
                for (QueryItem item : queryItems) {
                    Predicate predicate = null;
                    if (item.getOperatorType() != null) {
                        switch (item.getOperatorType()) {
                            case LIKE:
                                predicate = cb.like(root.get(item.getField()), "%" + item.getValue() + "%");
                                break;
                            case GREATER_THAN:
                                predicate = cb.gt(root.get(item.getField()), (Number) item.getValue());
                                break;
                            case LESS_THAN:
                                predicate = cb.lt(root.get(item.getField()), (Number) item.getValue());
                                break;
                            case GREATER_THAN_OR_EQUAL:
                                predicate = cb.ge(root.get(item.getField()), (Number) item.getValue());
                                break;
                            case LESS_THAN_OR_EQUAL:
                                predicate = cb.le(root.get(item.getField()), (Number) item.getValue());
                                break;
                            default:
                                predicate = cb.equal(root.get(item.getField()), item.getValue());
                                break;
                        }
                    } else {
                        predicate = cb.equal(root.get(item.getField()), item.getValue());
                    }
                    predicates.add(predicate);
                }
                query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                return query.getRestriction();
            }
        };
        return specification;
    }


    private boolean CheckFieldsAndValues(Object... fieldsAndValues) {
        if (fieldsAndValues.length % 2 != 0) {
            throw new IllegalArgumentException(PARAMS_NUMBERS_ERROR);
        } else {
            return true;
        }
    }


}
