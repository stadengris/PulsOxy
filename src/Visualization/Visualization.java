package Visualization;

import Controller.Alarm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Visualization {
    private JPanel mainPanel;
    private JButton bPause;
    private JButton bDeactivate;
    private JButton bSave;
    private JTextField tfAge;
    private Controller.Alarm alarm;
    private int age;

    public Visualization() {

        alarm = new Alarm();
        age = -1;

        bPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    public static void main(String[] args){
        JFrame frame = new JFrame("PulsOxy");
        frame.setContentPane(new Visualization().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
