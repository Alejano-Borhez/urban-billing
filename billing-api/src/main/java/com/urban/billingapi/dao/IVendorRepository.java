package com.urban.billingapi.dao;

import java.util.Collection;
import java.util.Set;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.vendor.Transport;
import com.urban.billingapi.model.vendor.Vendor;

import io.swagger.annotations.Api;

@RepositoryRestResource(path = "vendors")
@Api(tags = "Vendor Entity")
public interface IVendorRepository extends IBillingRepository<Vendor, Long> {

    Page<Vendor> findDistinctBySupportedTransports_NameIn(Collection<TransportType> names, Pageable pageable);

}
