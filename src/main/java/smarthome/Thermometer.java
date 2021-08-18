package smarthome;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Thermometer {
    private URL url;
    private HttpClient httpClient;

    public Thermometer(URL url) {
        this.url = url;
        this.httpClient = HttpClient.newHttpClient();
    }

    public float currentTemp() {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(URI.create(url + "/currentTemp"))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return Float.parseFloat(String.valueOf(response));
    }
}
