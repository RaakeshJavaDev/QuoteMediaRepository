package com.quotemedia.interview.quoteservice.api.Repository;

import com.quotemedia.interview.quoteservice.api.model.QuoteEntity;
import com.quotemedia.interview.quoteservice.api.model.QuotePrimeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, QuotePrimeKey> {



    @Query("SELECT q FROM QuoteEntity q WHERE q.id.symbol = :symbol ORDER BY q.id.day DESC")
    List<QuoteEntity> findLatestQuotesBySymbol(@Param("symbol") String symbol);


    @Query("SELECT q FROM QuoteEntity q WHERE q.id.day = :day ORDER BY q.ask DESC")
    List<QuoteEntity> findByDayOrderByAskDesc(@Param("day") LocalDate day);


}

