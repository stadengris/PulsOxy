package SignalProcessing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pulse implements Signal{
    private List<Short> pulseData;
    private List<String> pulseDate;
    private List<String> pulseDiagnosis;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Pulse(){
        this.pulseData = new ArrayList<Short>();
        this.pulseDate = new ArrayList<String>();
        this.pulseDiagnosis = new ArrayList<String>();
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

    @Override
    public void appendDiagnosis(String diagnosis){
        this.pulseDiagnosis.add(diagnosis);
    }

    @Override
    public List<String> getDiagnosis(){ return pulseDiagnosis; }

    public List<String> getDate() {return pulseDate;}

}
