package com.quotemedia.interview.quoteservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class QuotePrimeKey implements Serializable {


    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "DAY")
    private LocalDate day;

}
