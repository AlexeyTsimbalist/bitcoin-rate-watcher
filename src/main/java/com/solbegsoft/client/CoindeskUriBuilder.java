package com.solbegsoft.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

import static com.solbegsoft.util.Constants.NOT_EMPTY_INPUT;

public class CoindeskUriBuilder {
    
    private static final String RATE_FOR_PERIOD_URL = "https://api.coindesk.com/v1/bpi/historical/close.json" +
            "?start=%s&end=%s&currency=%s";
    
    private static final String CURRENT_RATE_URL = "https://api.coindesk.com/v1/bpi/currentprice/%s.json";
    
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    
    public URI buildCurrentRateUri(String currency) throws URISyntaxException {
        validateCurrency(currency);
        
        String uri = new Formatter().format(CURRENT_RATE_URL, currency).toString();
        
        return new URI(uri);
    }
    
    public URI buildRateForPeriodUri(String currency) throws URISyntaxException {
        validateCurrency(currency);
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        
        LocalDate today = LocalDate.now();
        LocalDate monthAgo = today.minusDays(30);
        
        String todayArgument = dateTimeFormatter.format(today);
        String monthAgoArgument = dateTimeFormatter.format(monthAgo);
        
        String uri = new Formatter().format(RATE_FOR_PERIOD_URL, monthAgoArgument, todayArgument, currency).toString();
        
        return new URI(uri);
    }
    
    private void validateCurrency(String currency) {
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException(NOT_EMPTY_INPUT);
        }
    }
}
