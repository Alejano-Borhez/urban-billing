package com.urban.billingapi.vo;

import com.urban.billingapi.model.enums.TransportType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportVO
{
    private Long id;
    private TransportType name;
}
