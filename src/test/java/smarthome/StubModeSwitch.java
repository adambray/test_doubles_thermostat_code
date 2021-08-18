package smarthome;

public class StubModeSwitch implements ModeSwitch {
    private final Mode mode;

    public StubModeSwitch(Mode mode) {
        this.mode = mode;
    }

    @Override
    public Mode currentMode() {
        return this.mode;
    }
}
