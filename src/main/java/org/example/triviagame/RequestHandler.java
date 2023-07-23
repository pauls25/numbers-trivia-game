package org.example.triviagame;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String NUMBERS_API_URL = "http://numbersapi.com/";
    private static final String URLPrompt = "random/trivia?min=0&max=2147483647";

    public static String getTrivia()
            throws URISyntaxException, IOException, InterruptedException {

        HttpRequest triviaRequest = HttpRequest.newBuilder()
                .uri(new URI(NUMBERS_API_URL + URLPrompt))
                .GET()
                .build();

        HttpResponse<String> triviaResponse = client.send(triviaRequest, HttpResponse.BodyHandlers.ofString());

        return triviaResponse.body();
    }

    public static Map<String, String> splitResponse(String response) {
        int firstSpaceIndex = response.indexOf(' ');

        String triviaNumber = response.substring(0, firstSpaceIndex);
        String triviaFact = response.substring(firstSpaceIndex);

        Map<String, String> questionInfo = new HashMap<>();
        questionInfo.put("triviaNumber", triviaNumber);
        questionInfo.put("triviaFact", triviaFact);

        return questionInfo;
    }
}
