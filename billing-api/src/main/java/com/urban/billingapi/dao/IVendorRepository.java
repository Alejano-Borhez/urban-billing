package com.urban.billingapi.dao;

import javax.transaction.Transactional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.urban.billingapi.model.vendor.Vendor;

import io.swagger.annotations.Api;

@RepositoryRestResource(path = "vendors")
@Api(tags = "Vendor Entity")
@Transactional
public interface IVendorRepository extends IBillingRepository<Vendor, Long> {
}
