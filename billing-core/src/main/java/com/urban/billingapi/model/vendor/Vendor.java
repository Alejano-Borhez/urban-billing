package com.urban.billingapi.model.vendor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vendor_transport",
            joinColumns = {@JoinColumn(name = "vendor_id")},
            inverseJoinColumns = {@JoinColumn(name = "transport_id")})
    private Set<Transport> supportedTransports = new HashSet<>();
}
