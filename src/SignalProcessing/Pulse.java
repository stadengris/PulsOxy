package SignalProcessing;

import Controller.Limits;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Pulse stores a recorded pulse data from a pulseoxymeter.
 */
public class Pulse implements Signal{
    /**
     * A list for storing the transmitted pulse data.
     */
    private List<Short> pulseData;
    /**
     * A list for storing the timepoint of the transmitted data.
     */
    private List<String> pulseDate;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Pulse(){
        this.pulseData = new ArrayList<Short>();
        this.pulseDate = new ArrayList<String>();
    }

    @Override
    public List<Short> getData() {
        return pulseData;
    }

    @Override
    public void append(Short d) {
        this.pulseData.add(d);
        this.pulseDate.add(formatter.format(new Date()));
    }

    @Override
    public Short getLatest() {
        return this.pulseData.get(this.pulseData.size()-1);
    }

    public List<String> getDate() {return pulseDate;}

}
