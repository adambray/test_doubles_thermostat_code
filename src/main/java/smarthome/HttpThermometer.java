package smarthome;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpThermometer implements Thermometer {
    private URL url;
    private HttpClient httpClient;

    public HttpThermometer(URL url) {
        this.url = url;
        this.httpClient = HttpClient.newHttpClient();
    }

    public float currentTemp() {
        var request = HttpRequest.newBuilder(URI.create(url + "/currentTemp"))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return Float.parseFloat(String.valueOf(response));
    }
}
