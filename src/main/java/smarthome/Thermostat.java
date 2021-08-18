package smarthome;

public class Thermostat {
    private final HttpModeSwitch modeSwitch;
    private final HeatController heatController;
    private final AirConditioningController airConditioningController;
    private HttpThermometer thermometer;

    public Thermostat(HttpModeSwitch modeSwitch,
                      HeatController heatController,
                      AirConditioningController airConditioningController,
                      HttpThermometer thermometer) {
        this.modeSwitch = modeSwitch;
        this.heatController = heatController;
        this.airConditioningController = airConditioningController;
        this.thermometer = thermometer;
    }

    public void run(float targetTemp) {
        if (modeSwitch.currentMode() == Mode.HEAT && thermometer.currentTemp() < targetTemp) {
            heatController.turnOn();
        }

        if (modeSwitch.currentMode() == Mode.COOL && thermometer.currentTemp() > targetTemp) {
            airConditioningController.turnOn();
        }

        if (modeSwitch.currentMode() == Mode.OFF) {
            heatController.turnOff();
            airConditioningController.turnOff();
        }
    }
}
