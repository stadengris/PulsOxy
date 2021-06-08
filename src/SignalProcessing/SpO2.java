package SignalProcessing;

import java.util.List;

public class SpO2 implements Signal{
    private List<Short> SpO2Data;

    @Override
    public List<Short> getData() {
        return SpO2Data;
    }

    @Override
    public void append(Short d) {
        this.SpO2Data.add(d);
    }

    @Override
    public Short getLatest() {
        return this.SpO2Data.get(this.SpO2Data.size()-1);
    }
}
