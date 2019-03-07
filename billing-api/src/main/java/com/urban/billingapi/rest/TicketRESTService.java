package com.urban.billingapi.rest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.urban.billingapi.dao.ITicketRepository;
import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.vendor.Ticket;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping("/api/ticket")
@Api
public class TicketRESTService implements BillingRESTService<Ticket, Long> {
    private final ITicketRepository repository;

    @GetMapping("/{city}")
    public Page<Ticket> findByTransportTypesAndCity(@RequestParam List<TransportType> transportTypes, @PathVariable String city, @RequestParam int page, @RequestParam int size) {
        return repository.findDistinctByTransports_NameInAndVendor_City_Name(transportTypes, city, PageRequest.of(page, size));
    }

}
