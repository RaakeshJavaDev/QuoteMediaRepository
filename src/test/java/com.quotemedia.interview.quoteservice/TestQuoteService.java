
package com.quotemedia.interview.quoteservice;


import com.quotemedia.interview.quoteservice.api.Repository.QuoteRepository;
import com.quotemedia.interview.quoteservice.api.model.QuoteEntity;
import com.quotemedia.interview.quoteservice.api.service.QuoteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestQuoteService {

    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    private QuoteService quoteService;

    @BeforeEach
    public void setup() {
        QuoteEntity quote1 = new QuoteEntity();
        // Initialize quote1 object

        QuoteEntity quote2 = new QuoteEntity();
        // Initialize quote2 object

        when(quoteRepository.findLatestQuotesBySymbol("AAPL")).thenReturn(Arrays.asList(quote1,quote2));
    }

    @Test
    public void getLatestQuotesBySymbolTest() {
        List<QuoteEntity> quotes = quoteService.getLatestQuotesBySymbol("AAPL");

        verify(quoteRepository, times(1)).findLatestQuotesBySymbol("AAPL");
        assertEquals(2, quotes.size());
    }

//    @Test
//    public void getSymbolWithHighestAskForDayTest(){
//
//        when(quoteRepository.findByDayOrderByAskDesc(LocalDate.of(2020, 1, 1))).thenReturn(Arrays.asList(new QuoteEntity(5.56)));
//
//    }
}

