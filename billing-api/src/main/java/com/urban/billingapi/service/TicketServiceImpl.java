package com.urban.billingapi.service;

import com.urban.billingapi.dao.ITicketRepository;
import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.vendor.Ticket;
import com.urban.billingapi.service.mapper.TicketVOMapper;
import com.urban.billingapi.vo.TicketVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl extends CrudService<Long, Ticket, TicketVO>
{
    @Getter
    private final TicketVOMapper mapper;
    @Getter
    private final ITicketRepository repository;

    public Page<TicketVO> customSearch( List<TransportType> names, String city, Duration duration, Pageable pageable )
    {
        return repository.findDistinctByTransports_NameInAndVendor_City_NameAndDurationGreaterThan( names, city, duration, pageable )
                         .map( mapper::toVO );
    }
}
