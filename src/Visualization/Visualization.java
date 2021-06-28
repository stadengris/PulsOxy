package Visualization;

import Controller.Alarm;
import Controller.Limits;
import Data.StoreData;
import SignalProcessing.Pulse;
import SignalProcessing.SpO2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Visualization {
    private JPanel mainPanel;
    private JButton bPause;
    private JButton bDeactivate;
    private JButton bSave;
    private JTextField tfAge;
    private JLabel upperLimitPulse;
    private JLabel upperLimitSpo2;
    private JLabel lowerLimitPulse;
    private JLabel lowerLimitSpo2;
    private JLabel valuePulse;
    private JLabel valueSpo2;
    private Controller.Alarm alarm;
    private int age;
    private Timer timer;
    private Pulse pulse;
    private SpO2 spO2;

    public Visualization() {

        alarm = new Alarm();
        age = -1;
        timer = new Timer();

        bPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarm.setPaused(true);
                pauseAlarm();
                JOptionPane.showMessageDialog(null, "The alarm is paused for 5 min.");
            }
        });

        bDeactivate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!alarm.isDeactivated()){
                    bDeactivate.setText("Activate Alarm");
                    JOptionPane.showMessageDialog(null, "The alarm is deactivated. \n Click Activate Alarm for activating.");
                    alarm.deactivate();
                } else {
                    bDeactivate.setText("Deactivate Alarm");
                    JOptionPane.showMessageDialog(null, "The alarm is activated. \n Click Deactivate Alarm for deactivating.");
                    alarm.activate();
                }

            }
        });

        tfAge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAgeSet()){
                    age = Integer.parseInt(tfAge.getText());
                    alarm.getLimits().initializeLimits(age);
                    upperLimitPulse.setText(Short.toString(Limits.getUpperPulse()));
                    upperLimitSpo2.setText(Short.toString(Limits.getUpperSpO2()));
                    lowerLimitPulse.setText(Short.toString(Limits.getLowerPulse()));
                    lowerLimitSpo2.setText(Short.toString(Limits.getLowerSpO2()));
                    valuePulse.setText(Short.toString(pulse.getLatest()));
                    valueSpo2.setText(Short.toString(spO2.getLatest()));
                } else{
                    tfAge.setText(Integer.toString(age));
                    JOptionPane.showMessageDialog(null, "The age is already specified.");
                }
            }
        });
    }

    public boolean isAgeSet(){
        if (age == -1){
            return false;
        }
        return true;
    }

    public int getAge(){
        return age;
    }

    public void triggerAlarm(){
        if (alarm.isPulseExceeded() && (!alarm.isDeactivated() || !alarm.isPaused())){
            //TODO: Label Pulse Limits aufleuchten!
        }
        if (alarm.isSpO2Exceeded() && (!alarm.isDeactivated() || !alarm.isPaused())){
            //TODO: Label Pulse Limits aufleuchten!
        }
    }

    public void pauseAlarm(){
        if (alarm.isPaused() && !alarm.isDeactivated()){
            alarm.setPaused(true);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    alarm.setPaused(false);
                    JOptionPane.showMessageDialog(null, "Alarm is activated again.");
                }
            };
            long delay = 300000L; // 5 min = 300000 millisec
            timer.schedule(task, delay);
        }
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("PulsOxy");
        frame.setContentPane(new Visualization().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        StoreData data = new StoreData(); // you will find a text file in your project folder if you run the main method; it will be empty beacuse we still have no input data
        data.WriteOutputData();
    }

}
