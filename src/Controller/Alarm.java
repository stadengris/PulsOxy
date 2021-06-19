package Controller;

import SignalProcessing.Pulse;
import SignalProcessing.Signal;
import SignalProcessing.SpO2;

public class Alarm {

    private boolean paused;
    private boolean alarm;
    private boolean deactivated;
    private Pulse pulse;
    private SpO2 oxygen;
    private Limits limits;

    public Alarm (){
        this.paused = false;
        this.deactivated = false;
        this.pulse = new Pulse();
        this.oxygen = new SpO2();
        this.limits = new Limits();
    }


    public boolean isPulseExceeded() { // The last value of pulse will be compared with the limits to check if they will be exceeded

        if (pulse.getLatest() > limits.getLowerPulse() && pulse.getLatest() < limits.getUpperPulse()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isSpO2Exceeded() { // The last value of SpO2 will be compared with the limits to check if they will be exceeded

        if (oxygen.getLatest() > limits.getLowerSpO2() && oxygen.getLatest() < limits.getUpperSpO2()) {
            return false;
        } else {
            return true;
        }
    }

    public void deactivate(){
        this.deactivated = true;
    }

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

    public void setDeactivated(boolean paused) {
        this.deactivated = deactivated;
    }

    public void setAlarm(boolean paused) {
        this.alarm = alarm;
    }

    public Limits getLimits(){return limits;}
}
