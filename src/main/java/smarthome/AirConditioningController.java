package smarthome;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublishers.ofString;

public class AirConditioningController {
    private URL url;
    private HttpClient httpClient;

    public AirConditioningController(URL url) {
        this.url = url;
        this.httpClient = HttpClient.newHttpClient();
    }


    public void turnOn() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/airConditioner?power=on"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnOff() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/airConditioner?power=off"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
