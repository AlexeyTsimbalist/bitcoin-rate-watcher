package com.solbegsoft.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CoindeskClient {
    
    private CoindeskUriBuilder uriBuilder = new CoindeskUriBuilder();
    
    public void getInfo(String currency) {
    
    }
    
    public String getCurrentInfo(String currency) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.buildCurrentRateUri(currency))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response.body();
    }
    
    public String getInfoForMonth(String currency) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.buildRateForPeriodUri(currency))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response.body();
    }
}
