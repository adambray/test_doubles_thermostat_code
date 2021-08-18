package smarthome;

public class SpyAirConditioningController implements AirConditioningController {
    public int turnOffCallCount = 0;
    public int turnOnCallCount = 0;

    @Override
    public void turnOn() {
        turnOnCallCount++;
    }

    @Override
    public void turnOff() {
        turnOffCallCount++;
    }
}
