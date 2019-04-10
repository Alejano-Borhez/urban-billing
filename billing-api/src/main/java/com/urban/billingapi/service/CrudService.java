package com.urban.billingapi.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.urban.billingapi.model.EntityExample;
import com.urban.billingapi.model.exceptions.UrbanServiceException;
import com.urban.billingapi.service.mapper.EntityToVOMapper;

@Validated
@Transactional
public abstract class CrudService<ID, E extends EntityExample<E, ID>, V> implements IBillingService<E, ID> {
    abstract EntityToVOMapper<ID, E, V> getMapper();

    public V create(@Valid @NotNull V vo) {
        return getMapper().toVO(createEntity(getMapper().toEntity(vo)));
    }

    private E createEntity(@Valid @NotNull E entity) {
        validate(entity, true);
        return getRepository().save(entity);
    }

    public V update(@Valid @NotNull V vo) {
        return getMapper().toVO(updateEntity(getMapper().toEntity(vo)));
    }

    private E updateEntity(@Valid @NotNull E entity) {
        validate(entity, false);
        return getRepository().save(entity);
    }

    public V get(@NotNull ID id) {
        E entity = getRepository().findById(id).orElseThrow(() -> new UrbanServiceException("not found entity with id=" + id));
        return getMapper().toVO(entity);
    }

    public Page<V> get(Pageable pageable) {
        Page<E> entities = getRepository().findAll(pageable);
        return entities.map(getMapper()::toVO);
    }

    public Page<V> find(Pageable pageable, Example<E> example) {
        Page<E> entities = getRepository().findAll(example, pageable);
        return entities.map(getMapper()::toVO);
    }

}
