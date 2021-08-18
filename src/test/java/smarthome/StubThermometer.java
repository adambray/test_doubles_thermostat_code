package smarthome;

public class StubThermometer implements Thermometer {
    private float temp;

    public StubThermometer(float temp) {
        this.temp = temp;
    }

    @Override
    public float currentTemp() {
        return this.temp;
    }
}
