package com.quotemedia.interview.quoteservice;

import com.quotemedia.interview.quoteservice.api.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QuoteApplication {

    private  static final Logger logger = LoggerFactory.getLogger(QuoteApplication.class);
    public static void main(final String[] args) {
        logger.info("QuoteApplication  Started ...");

        SpringApplication.run(QuoteApplication.class, args);
    }
}