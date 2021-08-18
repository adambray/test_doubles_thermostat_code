package smarthome;

public class Thermostat {
    private final ModeSwitch modeSwitch;
    private final HeatController heatController;
    private final AirConditioningController airConditioningController;
    private final Thermometer thermometer;
    private float targetTemp;

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
        this.targetTemp = targetTemp;

        switch (modeSwitch.currentMode()) {
            case OFF:
                turnOffAll();
                break;
            case HEAT:
                if (tooCold()) {
                    heatController.turnOn();
                } else {
                    heatController.turnOff();
                }
                break;
            case COOL:
                if (tooHot()) {
                    airConditioningController.turnOn();
                } else {
                    airConditioningController.turnOff();
                }
                break;
        }
    }

    private void turnOffAll() {
        heatController.turnOff();
        airConditioningController.turnOff();
    }

    private boolean tooCold() {
        return this.thermometer.currentTemp() < this.targetTemp;
    }

    private boolean tooHot() {
        return this.thermometer.currentTemp() > this.targetTemp;
    }
}
