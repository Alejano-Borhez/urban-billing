package com.urban.billingapi.service;

import static java.lang.String.format;

import com.urban.billingapi.dao.IBillingRepository;
import com.urban.billingapi.model.EntityExample;
import com.urban.billingapi.model.exceptions.UrbanServiceException;

public interface IBillingService<E extends EntityExample<E, ID>, ID> {
    IBillingRepository<E, ID> getRepository();
    default void validate(E entity, boolean isNew) {
        if (entity == null) throw new UrbanServiceException("Validation failed: entity is null");
        if (isNew && entity.getId() != null)
            throw new UrbanServiceException(format("Validation for %s failed as it is new and has id %s", entity.getClass().getSimpleName(), entity.getId()));
    }

    default E createNew(E entity) {
        validate(entity, true);
        return getRepository().save(entity);
    }

    default E update(E entity) {
        validate(entity, false);
        return getRepository().save(entity);
    }
}
