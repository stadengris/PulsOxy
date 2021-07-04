package SignalProcessing;

import java.util.ArrayList;
import java.util.List;

/**
 * SpO2 stores a recorded SpO2 data from a pulseoxymeter.
 */
public class SpO2 implements Signal{
    /**
     * A list for storing the transmitted SpO2 data.
     */
    private List<Short> oxygenData;

    public SpO2(){
        this.oxygenData = new ArrayList<Short>();
    }

    @Override
    public List<Short> getData() {
        return oxygenData;
    }

    @Override
    public void append(Short d) {
        this.oxygenData.add(d);
    }

    @Override
    public Short getLatest() {
        return this.oxygenData.get(this.oxygenData.size()-1);
    }

}
