package com.urban.billingapi.service.v1.mapper;

import com.urban.billingapi.model.enums.TicketType;
import com.urban.billingapi.model.vendor.Ticket;
import com.urban.billingapi.vo.TicketVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Currency;

@SpringBootTest
@RunWith( SpringRunner.class )
public class TicketVOMapperTest
{
    @Autowired TicketVOMapper mapper;

    @Test
    public void toVO()
    {
        TicketVO ticketVO=TicketVO.builder()
            .ticketType( TicketType.MULTI )
            .currency( Currency.getInstance( "USD" ) )
            .duration( Duration.of( 90, ChronoUnit.MINUTES ) )
            .price( BigDecimal.valueOf( 10 ) )
            .transportIds( Sets.newSet( 1L, 2L ) )
            .vendorId( 2L )
            .build();
        System.out.println(ticketVO);
        Ticket ticket = mapper.toEntity( ticketVO );
        System.out.println(ticket);
        TicketVO copyTicketVO = mapper.toVO( ticket );
        System.out.println(copyTicketVO);
        Assert.assertEquals( copyTicketVO,ticketVO );

    }
}