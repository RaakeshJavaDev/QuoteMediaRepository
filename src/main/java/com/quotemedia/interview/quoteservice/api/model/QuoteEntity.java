package com.quotemedia.interview.quoteservice.api.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "QUOTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteEntity {
    @EmbeddedId
    private QuotePrimeKey id;

    @Column(name = "BID")
    private BigDecimal bid;

    @Column(name = "ASK")
    private BigDecimal ask;



}
