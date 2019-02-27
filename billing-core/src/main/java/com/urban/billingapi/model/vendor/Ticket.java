package com.urban.billingapi.model.vendor;

import java.time.Duration;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.urban.billingapi.model.enums.TicketType;

import lombok.Data;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ticket_transport",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "transport_id"))
    private Set<Transport> transports;
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;
    private Duration duration;
    @OneToOne
    private Vendor vendor;
}
