package api;

import api.weatherApi.CallingApiException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class ApiBase {

    protected ObjectMapper objectMapper =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    protected String callApi(String link) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new CallingApiException("Calling API failed", e.getCause());
        }
        return response.body();
    }

    public <T> T convertJsonToJava(String body, Class<T> type) {
        try {
            return objectMapper.readValue(body, type);
        } catch (Exception e) {
            throw new CallingApiException("Conversion from Json to Java failed");
        }
    }
}
