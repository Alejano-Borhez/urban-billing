package com.urban.billingapi.service.v1;

import com.urban.billingapi.dao.ITicketRepository;
import com.urban.billingapi.model.vendor.Ticket;
import com.urban.billingapi.service.v1.CrudService;
import com.urban.billingapi.service.v1.mapper.TicketVOMapper;
import com.urban.billingapi.vo.TicketVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl extends CrudService<Long,Ticket,TicketVO>
{
    @Getter
    private final TicketVOMapper mapper;
    @Getter
    private final ITicketRepository repository;
}
