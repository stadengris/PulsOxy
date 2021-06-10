package Controller;

import SignalProcessing.Pulse;
import SignalProcessing.Signal;
import SignalProcessing.SpO2;

public class Alarm {

    private boolean pause;
    private boolean alarm;
    private boolean deactivate;
    private Pulse pulse;
    private SpO2 oxygen;
    private Limits limits;

    public boolean isExceeded() { // The last value of SpO2 & pulse will be compared with the limits to check if they will be exceeded

        if (pulse.getLatest() > limits.getLowerPulse() && pulse.getLatest() < limits.getUpperPulse()) {
            return false;
        } else if (oxygen.getLatest() > limits.getLowerSp02() && oxygen.getLatest() < limits.getUpperSp02()) {
            return false;
        } else {
            return true;
        }
    }

    public void triggerAlarm(){

    }

}
