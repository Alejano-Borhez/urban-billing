package com.urban.billingapi.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.vendor.Transport;

import io.swagger.annotations.Api;

@RepositoryRestResource(path = "users")
@Api(tags = "Transport Entity")
public interface ITransportRepository extends IBillingRepository<Transport, Long> {
    List<Transport> findAllByNameIn(Collection<TransportType> name);
}
