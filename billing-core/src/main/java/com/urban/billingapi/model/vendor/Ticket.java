package com.urban.billingapi.model.vendor;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Currency;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.urban.billingapi.model.EntityExample;
import com.urban.billingapi.model.enums.TicketType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements EntityExample<Ticket, Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ticket_transport",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "transport_id"))
    private Set<Transport> transports;
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;
    private Duration duration;
    private Currency currency;
    private BigDecimal price;
    @ManyToOne
    private Vendor vendor;
}
