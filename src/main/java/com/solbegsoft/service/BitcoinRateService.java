package com.solbegsoft.service;

import com.solbegsoft.client.CoindeskClient;
import com.solbegsoft.util.JsonResponseParser;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Map;

public class BitcoinRateService {
    
    private static final String BITCOIN_RATE_SCOREBOARD = "Currency: %s\n" +
            "Current: %f\n" +
            "Lowest for month: %f (%s)\n" +
            "Highest for month: %f (%s)\n";
    
    private JsonResponseParser parser = new JsonResponseParser();
    private CoindeskClient client = new CoindeskClient();
    
    public String fetchInfoByCurrency(String currency) throws IOException, URISyntaxException, InterruptedException {
        String currentInfo = client.getCurrentInfo(currency);
        String infoForMonth = client.getInfoForMonth(currency);
        
        Double currentRate = parser.parseCurrentRateResponse(currentInfo, currency);
        Pair<Map.Entry<Double, String>, Map.Entry<Double, String>> ratePair =
                parser.parseRateForMonthResponse(infoForMonth);
        
        return new Formatter().format(BITCOIN_RATE_SCOREBOARD, currency.toUpperCase(),
                currentRate,
                ratePair.getLeft().getKey(), ratePair.getLeft().getValue(),
                ratePair.getRight().getKey(), ratePair.getRight().getValue()).toString();
    }
    
    public JsonResponseParser getParser() {
        return parser;
    }
    
    public void setParser(JsonResponseParser parser) {
        this.parser = parser;
    }
    
    public CoindeskClient getClient() {
        return client;
    }
    
    public void setClient(CoindeskClient client) {
        this.client = client;
    }
}
