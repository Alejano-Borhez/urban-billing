package com.urban.billingapi.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.urban.billingapi.BillingPageRequest;
import com.urban.billingapi.dao.IBillingRepository;
import com.urban.billingapi.model.EntityExample;

public interface BillingRESTService<T extends EntityExample<T, ID>, ID> {
    IBillingRepository<T, ID> getRepository();

    @GetMapping(path = "all")
    default Page<T> findAll(@RequestParam int page, int size) {
        return getRepository().findAll(PageRequest.of(page, size));
    }

    @PostMapping(path = "find")
    default Page<T> find(@RequestBody BillingPageRequest<T, ID> pageRequest) {
        return getRepository().findAll(pageRequest.getExample(), pageRequest.getPageRequest());
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
