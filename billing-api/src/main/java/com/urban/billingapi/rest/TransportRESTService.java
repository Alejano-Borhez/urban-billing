package com.urban.billingapi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urban.billingapi.model.vendor.Transport;
import com.urban.billingapi.service.TransportServiceImpl;
import com.urban.billingapi.vo.TransportVO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping("/api/transport")
public class TransportRESTService extends AbstractRestService<Long, Transport, TransportVO> {
    @Getter
    private final TransportServiceImpl service;

}
