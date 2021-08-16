package smarthome;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static smarthome.Mode.*;

public class ModeSwitch {
    private URL url;

    public ModeSwitch(URL url) {
        this.url = url;
    }

    public Mode currentMode() {
        StringBuffer response = new StringBuffer();
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes("/currentMode");
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

        switch (String.valueOf(response)) {
            case "HEAT":
                return HEAT;
            case "COOL":
                return COOL;
            case "OFF":
            default:
                return OFF;
        }
    }
}
