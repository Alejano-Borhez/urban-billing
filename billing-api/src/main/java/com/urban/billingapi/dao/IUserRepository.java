package com.urban.billingapi.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.urban.billingapi.model.user.User;

import io.swagger.annotations.Api;

@RepositoryRestResource(path = "users")
@Api(tags = "User Entity")
public interface IUserRepository extends IBillingRepository<User, Long> {
}
