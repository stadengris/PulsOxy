package Controller;

import SignalProcessing.Pulse;
import SignalProcessing.SpO2;

/**
 * Class for managing the alarm.
 */
public class Alarm {

    /**
     * Boolean for pausing the alarm.
     */
    private boolean paused;
    /**
     * Boolean for deactivating and activating the alarm.
     */
    private boolean deactivated;
    /**
     * Limits parameter for checking the pulse and SpO2 signals.
     */
    private Limits limits;

    public Alarm (){
        this.paused = false;
        this.deactivated = false;
        this.limits = new Limits();
    }

    /**
     * The last value of pulse will be compared with the limits to check if they will be exceeded
     * @param pulse the Pulse signal that should be proved
     * @return      true if pulse is exceeded or false if not
     */
    public boolean isPulseExceeded(Pulse pulse) {
        if (pulse.getLatest() >= limits.getLowerPulse() && pulse.getLatest() <= limits.getUpperPulse()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * The last value of SpO2 will be compared with the limits to check if they will be exceeded.
     * @param oxygen    the SpO2 signal that should be proved
     * @return          true if oxygen is exceeded or false if not
     */
    public boolean isSpO2Exceeded(SpO2 oxygen) {
        if (oxygen.getLatest() >= limits.getLowerSpO2() && oxygen.getLatest() <= limits.getUpperSpO2()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method for deactivating the alarm.
     */
    public void deactivate(){
        this.deactivated = true;
    }

    /**
     * Method for activating the alarm.
     */
    public void activate(){
        this.deactivated = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public Limits getLimits() { return limits; }
}
