package Data;

import Controller.Alarm;
import SignalProcessing.Pulse;
import SignalProcessing.SpO2;
import Visualization.Visualization;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class StoreData {

    private Pulse pulse;
    private SpO2 spo2;
    private Alarm alarm;
    private Visualization visualization;
    String pulseAsk;
    String spO2Ask;

    public PrintWriter WriteOutputData() {
        try {
            String FileName = "Output-Data.txt";
            FileWriter fw = new FileWriter(FileName, true);
            PrintWriter writer = new PrintWriter(fw);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            if (alarm.isPulseExceeded()) {
                this.pulseAsk = "pulse exceeded!";
            } else {
                this.pulseAsk = "pulse not exceeded";
            }
            if (alarm.isSpO2Exceeded()) {
                this.spO2Ask = "SpO2 exceeded!";
            } else {
                this.spO2Ask = "SpO2 not exceeded";
            }
            for (int i = 0; i < pulse.getData().size(); i++) {
                writer.println("Age: " + visualization.getAge() + ", " + "Pulse: " + pulse.getData().get(i) +
                        ", " + "SpO2: " + spo2.getData().get(i) + ", " + pulseAsk + ", " + spO2Ask +
                        ", " + "time: " + formatter.format(date));
            }
            writer.close();
            return writer;
        } catch (IOException error) {
            JOptionPane.showConfirmDialog(null, error);
        }
        return null;
    }
}

    //        Logger logger = Logger.getLogger("MyLog");
//        FileHandler fh;
//
//        try {
//
//            // This block configure the logger with handler and formatter
//            fh = new FileHandler("myLog.log",true);
//            logger.addHandler(fh);
//            //logger.setLevel(Level.ALL);
//            SimpleFormatter formatter = new SimpleFormatter();
//            fh.setFormatter(formatter);
//
//            // the following statement is used to log any messages
//            logger.info("My first log");
//
//
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        logger.info("Hi How r u?");
//
//    }



