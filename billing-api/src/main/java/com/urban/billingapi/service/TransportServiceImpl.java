package com.urban.billingapi.service;

import org.springframework.stereotype.Service;

import com.urban.billingapi.dao.ITicketRepository;
import com.urban.billingapi.dao.ITransportRepository;
import com.urban.billingapi.model.vendor.Transport;
import com.urban.billingapi.service.mapper.TicketVOMapper;
import com.urban.billingapi.service.mapper.TransportVOMapper;
import com.urban.billingapi.vo.TransportVO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Getter
public class TransportServiceImpl extends CrudService<Long, Transport, TransportVO> {
    private final TransportVOMapper mapper;
    private final ITransportRepository repository;
}
