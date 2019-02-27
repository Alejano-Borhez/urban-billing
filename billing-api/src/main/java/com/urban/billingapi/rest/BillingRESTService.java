package com.urban.billingapi.rest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.urban.billingapi.BillingPageRequest;
import com.urban.billingapi.dao.IBillingRepository;

public interface BillingRESTService<T, ID> {
    IBillingRepository<T, ID> getRepository();

    RepositoryEntityLinks getRepositoryEntityLinks();

    Class<T> getEntityClass();

    @GetMapping(path = "all")
    default Resource<Page<T>> findAll(Pageable pageable) {
        return new Resource<>(getRepository().findAll(pageable), getRepositoryEntityLinks().linksToSearchResources(getEntityClass()));
    }

    @PostMapping(path = "find")
    default Resource<Page<T>> find(@RequestBody BillingPageRequest<T> pageRequest) {
        return new Resource<>(getRepository().findAll(Example.of(pageRequest.getEntityExample()), pageRequest.pageRequest()), getRepositoryEntityLinks().linksToSearchResources(getEntityClass()));
    }

    @PostMapping(path = "create")
    default T createNew(@RequestBody T entity) {
        return getRepository().save(entity);
    }

    @PutMapping(path = "edit")
    default T edit(@RequestBody T entity) {
        return getRepository().save(entity);
    }

    @DeleteMapping(path = "delete")
    default void delete(@RequestParam("id") ID id) {
        getRepository().deleteById(id);
    }
}
