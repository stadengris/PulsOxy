package SignalProcessing;

import java.util.ArrayList;
import java.util.List;

public class SpO2 implements Signal{
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
