package com.quotemedia.interview.quoteservice;

import com.quotemedia.interview.quoteservice.api.controller.QuoteController;
import com.quotemedia.interview.quoteservice.api.model.QuoteEntity;
import com.quotemedia.interview.quoteservice.api.service.QuoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import java.util.Collections;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestQuoteController {


    @InjectMocks
    private QuoteController quoteController;

    @Mock
    private QuoteService quoteService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(quoteController).build();
    }

    @Test
    public void testValidSymbolLength() throws Exception {
        String validSymbol = "AAPL";
        QuoteEntity dummyQuote = new QuoteEntity();

        when(quoteService.getLatestQuotesBySymbol(validSymbol.toUpperCase()))
                .thenReturn(Collections.singletonList(dummyQuote));

        mockMvc.perform(get("/symbols/" + validSymbol + "/quotes/latest"))

                .andExpect(status().isOk())

                .andExpect(   MockMvcResultMatchers.jsonPath("$.bid").value(dummyQuote.getBid())) // Match the bid value
                .andExpect( MockMvcResultMatchers.jsonPath("$.ask").value(dummyQuote.getAsk())); // Match the ask value

    }



    @Test
    public void testInvalidSymbolLength() throws Exception {
        String invalidSymbol = "A"; // Invalid symbol length

        mockMvc.perform(get("/symbols/{symbol}/quotes/latest",invalidSymbol))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Symbol is not valid.")));
    }

    @Test
    public void testEmptyQuotesList() throws Exception {
        String validSymbol = "GOOG";

        String checkValidSymbol = validSymbol.toUpperCase();

        when(quoteService.getLatestQuotesBySymbol(checkValidSymbol))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/symbols/" + validSymbol + "/quotes/latest"))
                .andExpect(status().isNotFound());
    }
}





