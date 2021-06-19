package Visualization;

import Controller.Alarm;

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
    private Controller.Alarm alarm;
    private int age;
    private Timer timer;

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

    public void triggerAlarm(){
        if (alarm.isPulseExceeded()){
            //TODO: Label Pulse Limits aufleuchten!
        }
        if (alarm.isSpO2Exceeded()){
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
    }
}
