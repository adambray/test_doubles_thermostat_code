package smarthome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThermostatTest {
    @Test
    void turnsOffHeatAndAC_whenOff() {
        var modeSwitch = new StubModeSwitch(Mode.OFF);
        var thermometer = new StubThermometer(68f);
        var heatController = new SpyHeatController();
        var airConditioningController = new SpyAirConditioningController();

        var thermostat = new Thermostat(modeSwitch, heatController, airConditioningController, thermometer);

        thermostat.run(72.0f);

        assertEquals(airConditioningController.turnOffCallCount, 1);
        assertEquals(heatController.turnOffCallCount, 1);
    }

    @Test
    void turnsHeatOn_whenTooCold() {
        var modeSwitch = new StubModeSwitch(Mode.HEAT);
        var thermometer = new StubThermometer(55.5f);
        var heatController = new SpyHeatController();
        var airConditioningController = new SpyAirConditioningController();

        Thermostat thermostat = new Thermostat(modeSwitch, heatController, airConditioningController, thermometer);

        thermostat.run(72.0f);

        assertEquals(heatController.turnOnCallCount, 1);
        assertEquals(airConditioningController.turnOnCallCount, 0);
    }

    @Test
    void turnsAirConditioningOn_whenTooHot() {
        var modeSwitch = new StubModeSwitch(Mode.COOL);
        var thermometer = new StubThermometer(72.5f);
        var heatController = new SpyHeatController();
        var airConditioningController = new SpyAirConditioningController();

        Thermostat thermostat = new Thermostat(modeSwitch, heatController, airConditioningController, thermometer);

        thermostat.run(72.0f);

        assertEquals(heatController.turnOnCallCount, 0);
        assertEquals(airConditioningController.turnOnCallCount, 1);
    }

    @Test
    void turnsAirConditioningOff_whenCorrectTemp() {
        var modeSwitch = new StubModeSwitch(Mode.COOL);
        var thermometer = new StubThermometer(72.0f);
        var heatController = new SpyHeatController();
        var airConditioningController = new SpyAirConditioningController();

        Thermostat thermostat = new Thermostat(modeSwitch, heatController, airConditioningController, thermometer);

        thermostat.run(72.0f);

        assertEquals(heatController.turnOnCallCount, 0);
        assertEquals(airConditioningController.turnOffCallCount, 1);
    }

    @Test
    void turnsHeatOff_whenCorrectTemp() {
        var modeSwitch = new StubModeSwitch(Mode.HEAT);
        var thermometer = new StubThermometer(72.0f);
        var heatController = new SpyHeatController();
        var airConditioningController = new SpyAirConditioningController();

        Thermostat thermostat = new Thermostat(modeSwitch, heatController, airConditioningController, thermometer);

        thermostat.run(72.0f);

        assertEquals(heatController.turnOffCallCount, 1);
        assertEquals(airConditioningController.turnOffCallCount, 0);
    }
}