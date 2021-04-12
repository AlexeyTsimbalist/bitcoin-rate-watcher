package com.solbegsoft.service;

import com.solbegsoft.client.CoindeskClient;
import com.solbegsoft.util.JsonResponseParser;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BitcoinRateServiceTest {
    
    private static final BitcoinRateService service = new BitcoinRateService();
    private static final CoindeskClient client = mock(CoindeskClient.class);
    private static final JsonResponseParser parser = mock(JsonResponseParser.class);
    
    @BeforeAll
    public static void init() {
        service.setParser(parser);
        service.setClient(client);
    }
    
    @Test
    public void shouldFetchInfoByCurrency() throws IOException, URISyntaxException, InterruptedException {
        String currency = "EUR";
        String currentInfo = "Current Info";
        String infoForMonth = "Info for month";
        
        String expectedResult = "Currency: EUR\n" +
                "Current: 25600.280000\n" +
                "Lowest for month: 12000.100000 (2001-01-01)\n" +
                "Highest for month: 16000.200000 (2001-01-31)\n";
        
        Double currentRate = 25600.28;
        
        TreeMap<Double, String> map = new TreeMap<>();
        map.put(12000.1, "2001-01-01");
        map.put(16000.2, "2001-01-31");
        
        Pair<Map.Entry<Double, String>, Map.Entry<Double, String>> pair = new ImmutablePair<>(map.firstEntry(), map.lastEntry());
        
        when(client.getCurrentInfo(currency)).thenReturn(currentInfo);
        when(client.getInfoForMonth(currency)).thenReturn(infoForMonth);
        
        when(parser.parseCurrentRateResponse(currentInfo, currency)).thenReturn(currentRate);
        when(parser.parseRateForMonthResponse(infoForMonth)).thenReturn(pair);
        
        String result = service.fetchInfoByCurrency(currency);
        
        assertEquals(expectedResult, result);
    }
}