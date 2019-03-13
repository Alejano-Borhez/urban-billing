package com.urban.billingapi.vo;

import com.urban.billingapi.model.enums.TicketType;
import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.vendor.Transport;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketVO
{
    private Long id;

    private Set<Long> transportIds=new HashSet<>(  );
    @NotNull
    private TicketType ticketType;
    @NotNull
    private Duration duration;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Currency currency;
    @NotNull
    private Long vendorId;
}
