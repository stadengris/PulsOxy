package SignalProcessing;

import Controller.Limits;

import java.util.List;

/**
 * The Signal interface implements a methods for storing signals like
 * - Pulse
 * - Oxygen
 * from a pulsoxymeter sensor.
 */
public interface Signal {

    /**
     * Method for getting the stored data.
     * @return the stored data
     */
    List<Short> getData();

    /**
     * Method for appending a new value to the stored data
     * @param d
     */
    void append(Short d);

    /**
     * Method for getting the latest stored value.
     * @return the latest stored value
     */
    Short getLatest();
}
