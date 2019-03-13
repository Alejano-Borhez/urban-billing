package com.urban.billingapi.dao;

import javax.transaction.Transactional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.urban.billingapi.model.vendor.City;

import io.swagger.annotations.Api;

import java.util.Optional;

@RepositoryRestResource( path = "cities" )
@Api( tags = "City Entity" )
@Transactional
public interface ICityRepository extends IBillingRepository<City, Long>
{
   public Optional<City> findByName( String name );
}
