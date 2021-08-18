package smarthome;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        var localServer = new URL("http://localhost:8080");

        var thermostat = new Thermostat(
          new ModeSwitch(localServer),
          new HeatController(localServer),
          new AirConditioningController(localServer),
          new Thermometer(localServer)
        );

        while(true) {
            thermostat.run(72.0f);
        }
    }
}
