package SignalProcessing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpO2 implements Signal{
    private List<Short> oxygenData;
    //private List<String> oxygenDate;
    //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private List<String> oxygenDiagnosis;

    public SpO2(){
        this.oxygenData = new ArrayList<Short>();
        //this.oxygenDate = new ArrayList<String>();
        this.oxygenDiagnosis = new ArrayList<String>();
    }

    @Override
    public List<Short> getData() {
        return oxygenData;
    }

    @Override
    public void append(Short d) {
        this.oxygenData.add(d);
        //this.oxygenDate.add(formatter.format(new Date()));
    }

    @Override
    public Short getLatest() {
        return this.oxygenData.get(this.oxygenData.size()-1);
    }

    @Override
    public void appendDiagnosis(String diagnosis){
        this.oxygenDiagnosis.add(diagnosis);
    }

    @Override
    public List<String> getDiagnosis(){ return oxygenDiagnosis; }

}
