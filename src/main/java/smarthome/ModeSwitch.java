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

import static smarthome.Mode.*;

public class ModeSwitch {
    private HttpClient httpClient;
    private URL url;

    public ModeSwitch(URL url) {
        this.url = url;
        this.httpClient = HttpClient.newHttpClient();
    }

    public Mode currentMode() {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(URI.create(url + "/currentMode"))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return switch (String.valueOf(response.body())) {
            case "HEAT" -> HEAT;
            case "COOL" -> COOL;
            default -> OFF;
        };
    }
}
