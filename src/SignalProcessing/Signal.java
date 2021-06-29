package SignalProcessing;

import Controller.Limits;

import java.util.List;

public interface Signal {
    List<Short> getData();
    void append(Short d, Limits limits);
    Short getLatest();
    List<String> getDiagnosis();
}
