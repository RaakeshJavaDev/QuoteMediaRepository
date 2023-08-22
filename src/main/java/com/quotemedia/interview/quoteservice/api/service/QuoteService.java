package com.quotemedia.interview.quoteservice.api.service;

import com.quotemedia.interview.quoteservice.api.Repository.QuoteRepository;
import com.quotemedia.interview.quoteservice.api.controller.QuoteController;
import com.quotemedia.interview.quoteservice.api.model.QuoteEntity;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;




@Service
//@Log4j
public class QuoteService {

    private  static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

        private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }


    @Cacheable(cacheNames = "GetSymbol",key="#symbol")
    public List<QuoteEntity> getLatestQuotesBySymbol(String symbol) {
       logger.info("fetching symbol data from database");
            return quoteRepository.findLatestQuotesBySymbol(symbol);
        }


    @Cacheable(cacheNames = "GetHighestAsk",key="#day")
    public QuoteEntity getSymbolWithHighestAskForDay(LocalDate day) {
        logger.info("fetching day data from database");
        List<QuoteEntity> quotes = quoteRepository.findByDayOrderByAskDesc(day);
        if (quotes.isEmpty()) {
            return null;
        }
        return quotes.get(0);
    }

    }


