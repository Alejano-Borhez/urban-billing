package com.urban.billingapi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.vendor.City;
import com.urban.billingapi.model.vendor.Ticket;

import io.swagger.annotations.Api;

@RepositoryRestResource(path = "tickets")
@Api(tags = "Ticket Entity")
@Transactional
public interface ITicketRepository extends IBillingRepository<Ticket, Long>{
    Page<Ticket> findDistinctByTransports_NameInAndVendor_City_Name(List<TransportType> names, String city, Pageable pageable);
}
