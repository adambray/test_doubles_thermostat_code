package smarthome;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Thermometer {
    private URL url;

    public Thermometer(URL url) {
        this.url = url;
    }

    public float currentTemp() {
        StringBuffer response = new StringBuffer();
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes("/currentTemp");
            out.flush();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Float.parseFloat(String.valueOf(response));
    }
}
