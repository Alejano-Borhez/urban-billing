package com.urban.billingapi.model;

import static javax.persistence.EnumType.STRING;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import com.urban.billingapi.model.enums.PaymentMethod;
import com.urban.billingapi.model.user.User;

import lombok.Data;

@Data
@Entity
public class PaymentSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_source_id")
    private Long id;
    @Enumerated(STRING)
    private PaymentMethod paymentMethod;
    private LocalDate expires;
    private String tokenId;
    private String imageUrl;
    @ManyToOne(optional = false)
    @JoinTable(name = "user_payment_source",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_source_id"))
    private User user;
}
