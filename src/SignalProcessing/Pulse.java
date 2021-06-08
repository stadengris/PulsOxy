package SignalProcessing;

import java.util.List;

public class Pulse implements Signal{
    private List<Short> PulseData;

    @Override
    public List<Short> getData() {
        return PulseData;
    }

    @Override
    public void append(Short d) {
        this.PulseData.add(d);
    }

    @Override
    public Short getLatest() {
        return this.PulseData.get(this.PulseData.size()-1);
    }
}
