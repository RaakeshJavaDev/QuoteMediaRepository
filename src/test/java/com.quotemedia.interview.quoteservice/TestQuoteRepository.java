package com.quotemedia.interview.quoteservice;

import com.quotemedia.interview.quoteservice.api.Repository.QuoteRepository;
import com.quotemedia.interview.quoteservice.api.model.QuoteEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;
@SpringBootTest
public class TestQuoteRepository {

    @Autowired
    private QuoteRepository quoteRepository;
    @Test
    public void testFindLatestQuotesBySymbol() {
        String symbol = "GOOG"; // replace with a valid symbol

        List<QuoteEntity> quotes = quoteRepository.findLatestQuotesBySymbol(symbol);

        assertNotNull(quotes);
        assertFalse(quotes.isEmpty());


    }


    @Test
    public void testFindByDayOrderByAskDesc() {
        LocalDate day = LocalDate.of(2020, 01, 01);

        List<QuoteEntity> quotes = quoteRepository.findByDayOrderByAskDesc(day);

        assertNotNull(quotes);
        assertFalse(quotes.isEmpty());


    }

}
