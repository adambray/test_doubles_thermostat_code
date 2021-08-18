package smarthome;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        var localServer = new URL("http://localhost:8080");

        var thermostat = new Thermostat(
          new HttpModeSwitch(localServer),
          new HttpHeatController(localServer),
          new HttpAirConditioningController(localServer),
          new HttpThermometer(localServer)
        );

        while(true) {
            thermostat.run(72.0f);
        }
    }
}
