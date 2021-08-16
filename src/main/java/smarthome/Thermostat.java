package smarthome;

public class Thermostat {
    private final ModeSwitch modeSwitch;
    private final HeatController heatController;
    private final AirConditioningController airConditioningController;
    private Thermometer thermometer;

    public Thermostat(ModeSwitch modeSwitch,
                      HeatController heatController,
                      AirConditioningController airConditioningController,
                      Thermometer thermometer) {
        this.modeSwitch = modeSwitch;
        this.heatController = heatController;
        this.airConditioningController = airConditioningController;
        this.thermometer = thermometer;
    }

    public void run(float targetTemp) {
        while (modeSwitch.currentMode() != Mode.OFF) {
            if (modeSwitch.currentMode() == Mode.HEAT && thermometer.currentTemp() < targetTemp) {
                heatController.turnOn();
            }

            if (modeSwitch.currentMode() == Mode.COOL && thermometer.currentTemp() > targetTemp) {
                airConditioningController.turnOn();
            }
        }
    }
}
