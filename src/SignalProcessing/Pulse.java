package SignalProcessing;

import Controller.Limits;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pulse implements Signal{
    private List<Short> pulseData;
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
