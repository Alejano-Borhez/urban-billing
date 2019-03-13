package com.urban.billingapi.rest.v1;

import com.urban.billingapi.model.vendor.Ticket;
import com.urban.billingapi.service.v1.TicketServiceImpl;
import com.urban.billingapi.vo.TicketVO;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping( "/api/ticket" )
@Api
public class TicketRESTService extends AbstractRestService<Long,Ticket,TicketVO>
{
    @Getter
    private final TicketServiceImpl service;
}
