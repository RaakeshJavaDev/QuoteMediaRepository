package com.quotemedia.interview.quoteservice.api.controller;


import com.quotemedia.interview.quoteservice.api.model.QuoteEntity;
import com.quotemedia.interview.quoteservice.api.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


    @RestController
    public class QuoteController {


        private  static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

        @Autowired
        private QuoteService quoteService;


        @GetMapping("/symbols/{symbol}/quotes/latest")
        public ResponseEntity<?> getLatestQuoteBySymbol(@PathVariable String symbol) {
            List<QuoteEntity> quotes = quoteService.getLatestQuotesBySymbol(symbol.toUpperCase());


            if (symbol.length() < 2 || symbol.length() > 6) {
                String errorMessage = "Symbol is not valid. It must be at least 2 characters and at most 6 characters.";

                logger.error("Symbol is not valid. It must be at least 2 characters and at most 6 characters");

                return ResponseEntity.badRequest()
                        .body(errorMessage);
            }

            if (quotes.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            QuoteEntity latestQuote = quotes.get(0);
            System.out.println(latestQuote);
            return ResponseEntity.ok(latestQuote);
        }


        @GetMapping("/symbols/quotes/highest-ask/{day}")
        public ResponseEntity<QuoteEntity> getSymbolWithHighestAsk(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day) {
            QuoteEntity quoteWithHighestAsk = quoteService.getSymbolWithHighestAskForDay(day);
            if (quoteWithHighestAsk == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(quoteWithHighestAsk);
        }

    }
