package smarthome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ThermostatTest {
    @Test
    void turnsOffHeatAndAC_whenOff() {
        ModeSwitch modeSwitch = mock(ModeSwitch.class);
        HeatController heatController = mock(HeatController.class);
        AirConditioningController airConditioningController = mock(AirConditioningController.class);
        Thermometer thermometer = mock(Thermometer.class);

        when(modeSwitch.currentMode()).thenReturn(Mode.OFF);

        Thermostat thermostat = new Thermostat(modeSwitch, heatController, airConditioningController, thermometer);

        thermostat.run(72.0f);

        verify(heatController).turnOff();
        verify(airConditioningController).turnOff();
    }

    @Test
    void turnsHeatOn_whenTooCold() {
        ModeSwitch modeSwitch = mock(ModeSwitch.class);
        HeatController heatController = mock(HeatController.class);
        AirConditioningController airConditioningController = mock(AirConditioningController.class);
        Thermometer thermometer = mock(Thermometer.class);

        when(modeSwitch.currentMode()).thenReturn(Mode.HEAT).thenReturn(Mode.OFF);
        when(thermometer.currentTemp()).thenReturn(55.0f);

        Thermostat thermostat = new Thermostat(modeSwitch, heatController, airConditioningController, thermometer);

        thermostat.run(72.0f);

        verify(heatController).turnOn();
        verify(airConditioningController).turnOff();
    }
}