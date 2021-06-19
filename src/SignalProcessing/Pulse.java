package SignalProcessing;

import java.util.ArrayList;
import java.util.List;

public class Pulse implements Signal{
    private List<Short> pulseData;

    public Pulse(){
        this.pulseData = new ArrayList<Short>();
    }

    @Override
    public List<Short> getData() {
        return pulseData;
    }

    @Override
    public void append(Short d) {
        this.pulseData.add(d);
    }

    @Override
    public Short getLatest() {
        return this.pulseData.get(this.pulseData.size()-1);
    }
}
