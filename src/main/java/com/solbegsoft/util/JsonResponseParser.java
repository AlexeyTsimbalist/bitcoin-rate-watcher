package com.solbegsoft.util;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static com.solbegsoft.util.Constants.NOT_EMPTY_INPUT;

public class JsonResponseParser {
    
    public Double parseCurrentRateResponse(String response, String currency) {
        validateResponse(response);
        
        final JSONObject jsonObject = new JSONObject(response);
        String stringValue = jsonObject.getJSONObject("bpi")
                .getJSONObject(currency.toUpperCase()).getString("rate");
        return Double.parseDouble(stringValue.replaceAll(",", ""));
    }
    
    public Pair<Map.Entry<Double, String>, Map.Entry<Double, String>> parseRateForMonthResponse(String response, String currency) {
        validateResponse(response);
        
        JSONObject bpi = new JSONObject(response).getJSONObject("bpi");
        
        Set<String> keys = bpi.keySet();
        TreeMap<Double, String> rateForMonth = new TreeMap<>();
        
        for (String date : keys) {
            double rate = bpi.getDouble(date);
            rateForMonth.put(rate, date);
        }
        
        Map.Entry<Double, String> lowestRate = rateForMonth.firstEntry();
        Map.Entry<Double, String> highestRate = rateForMonth.lastEntry();
        
        return new ImmutablePair<>(lowestRate, highestRate);
    }
    
    private void validateResponse(String currency) {
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException(NOT_EMPTY_INPUT);
        }
    }
}
