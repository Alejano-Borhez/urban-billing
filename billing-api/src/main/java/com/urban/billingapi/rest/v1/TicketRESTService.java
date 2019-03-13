package com.urban.billingapi.rest.v1;

import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.vendor.Ticket;
import com.urban.billingapi.service.v1.TicketServiceImpl;
import com.urban.billingapi.vo.TicketVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping( "/api/ticket" )
@Api
public class TicketRESTService extends AbstractRestService<Long, Ticket, TicketVO>
{
    @Getter
    private final TicketServiceImpl service;

    @GetMapping( "/search" )
    public Page<TicketVO> customSearch(
        @RequestParam List<TransportType> names,
        @RequestParam String city,
        @RequestParam( required = false ) Long durationMinutes,
        @PageableDefault Pageable pageable )
    {
        long checkedMinutes = durationMinutes != null ? durationMinutes : 0;
        return service.customSearch( names, city, Duration.of( checkedMinutes, ChronoUnit.MINUTES ), pageable );
    }
}
