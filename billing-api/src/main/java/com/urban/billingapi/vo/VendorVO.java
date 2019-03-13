package com.urban.billingapi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorVO
{
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Currency currency;
    @NotNull
    private String cityName;
}
