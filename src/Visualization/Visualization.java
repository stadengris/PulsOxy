package Visualization;

import Controller.Alarm;
import Controller.Limits;
import SignalProcessing.Pulse;
import SignalProcessing.SpO2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main class of the PulsOxy Software.
 * Manages mainly the visualization.
 */
public class Visualization {
    /**
     * Parameters of the GUI
     */
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
    private JLabel alarmPulseMsg;
    private JLabel alarmSpO2Msg;
    private JLabel lisActivated;
    /**
     * Alarm instance for managing alarm.
     */
    private Alarm alarm;
    /**
     * Integer for the age of the user.
     * Must be specified by the user before values will be recorded.
     */
    private int age;
    /**
     * Timer for pausing the alarm for 5 minutes.
     */
    private Timer timer;
    /**
     * Pulse instance for storing pulse signal.
     */
    private Pulse pulse;
    /**
     * SpO2 instance for storing SpO2 signal.
     */
    private SpO2 spO2;

    public Visualization() {

        alarm = new Alarm();
        age = -1;
        timer = new Timer();
        pulse = new Pulse();
        spO2 = new SpO2();

        /**
         * ActionListener for pausing the alarm.
         */
        bPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarm.setPaused(true);
                lisActivated.setText("Alarm is paused.");
                valuePulse.setForeground(Color.black);
                alarmPulseMsg.setForeground(new Color(187,187,187));
                alarmPulseMsg.setText("");
                valueSpo2.setForeground(Color.black);
                alarmSpO2Msg.setForeground(new Color(187,187,187));
                alarmSpO2Msg.setText("");
                pauseAlarm();
                JOptionPane.showMessageDialog(null, "The alarm is paused for 5 min.");
            }
        });

        /**
         * ActionListener for decativating / activating the alarm.
         */
        bDeactivate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!alarm.isDeactivated()) {
                    bDeactivate.setText("Activate Alarm"); // change "deactivate" to "activate" on button.
                    lisActivated.setText("Alarm is deactivated."); // inform that alarm is deactivated
                    valuePulse.setForeground(Color.black);

                    // Hide alarm on GUI.
                    alarmPulseMsg.setForeground(new Color(187,187,187));
                    alarmPulseMsg.setText("");
                    valueSpo2.setForeground(Color.black);
                    alarmSpO2Msg.setForeground(new Color(187,187,187));
                    alarmSpO2Msg.setText("");

                    alarm.deactivate();
                    if (alarm.isPaused()){
                        alarm.setPaused(false);
                    }

                    // Notify user.
                    JOptionPane.showMessageDialog(null, "The alarm is deactivated. " +
                            "\n Click Activate Alarm for activating.");
                } else {
                    bDeactivate.setText("Deactivate Alarm"); // change "activate" to "deactivate" on button.
                    lisActivated.setText("Alarm is activated."); // inform that alarm is activated.

                    alarm.activate();

                    // Notify user.
                    JOptionPane.showMessageDialog(null, "The alarm is activated. " +
                            "\n Click Deactivate Alarm for deactivating.");
                }

            }
        });

        /**
         * ActionListener for controlling if age is set:
         * Initialize and show the upper / lower limits of pulse and SpO2.
         */
        tfAge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAgeSet()) {
                    age = Integer.parseInt(tfAge.getText());
                    alarm.getLimits().initializeLimits(age);
                    upperLimitPulse.setText(Short.toString(Limits.getUpperPulse()));
                    upperLimitSpo2.setText(Short.toString(Limits.getUpperSpO2()));
                    lowerLimitPulse.setText(Short.toString(Limits.getLowerPulse()));
                    lowerLimitSpo2.setText(Short.toString(Limits.getLowerSpO2()));
                } else {
                    tfAge.setText(Integer.toString(age));
                    JOptionPane.showMessageDialog(null, "The age is already specified.");
                }
            }
        });

        /**
         * ActionListener for saving the recorded data.
         */
        bSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WriteOutputData();
            }
        });
    }

    /**
     * Method for checking weather user set his age.
     * @return true if age is set or false if not
     */
    public boolean isAgeSet() {
        if (age == -1) {
            return false;
        }
        return true;
    }

    public int getAge() {
        return age;
    }

    /**
     * Method for managing the alarm.
     * If pulse or SpO2 signals exceed their limits
     * and the alarm is not paused or deactivated
     * the alarm is triggered and visualized.
     */
    public void triggerAlarm() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (alarm.isPulseExceeded(pulse) && (!alarm.isDeactivated() && !alarm.isPaused())) {
                    valuePulse.setForeground(Color.RED);
                    alarmPulseMsg.setForeground(Color.RED);
                    alarmPulseMsg.setText("Pulse exceeded!");
                } else {
                    valuePulse.setForeground(Color.black);
                    alarmPulseMsg.setForeground(new Color(187,187,187));
                    alarmPulseMsg.setText("");
                }
                if (alarm.isSpO2Exceeded(spO2) && (!alarm.isDeactivated() && !alarm.isPaused())) {
                    valueSpo2.setForeground(Color.RED);
                    alarmSpO2Msg.setForeground(Color.RED);
                    alarmSpO2Msg.setText("SpO2 exceeded!");
                } else {
                    valueSpo2.setForeground(Color.black);
                    alarmSpO2Msg.setForeground(new Color(187,187,187));
                    alarmSpO2Msg.setText("");
                }
            }
        });
    }

    /**
     * Method for pausing the alarm.
     */
    public void pauseAlarm() {
        if (alarm.isPaused() && !alarm.isDeactivated()) {
            alarm.setPaused(true);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (!alarm.isDeactivated()) {
                        alarm.setPaused(false);
                        lisActivated.setText("Alarm is activated.");
                        JOptionPane.showMessageDialog(null, "Alarm is activated again.");
                    }
                }
            };
            long delay = 300000L; // 5 min = 300000 millisec
            timer.schedule(task, delay);
        }
    }

    /**
     * Method for generating a file containing the recorded data, alarm and other details.
     * @return a PrintWriter for writing the output file.
     */
    public PrintWriter WriteOutputData() {
        try {
            String FileName = "Output-Data.txt"; //default path for storing the file

            // open JFileChooser for letting user select a path.
            JFrame parentFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            int userSelection = fileChooser.showSaveDialog(parentFrame);

            // get users selected path and append ".txt" for generating a txt file.
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                FileName = fileToSave.getAbsolutePath() + ".txt";
            }

            // generate the file
            FileWriter fw = new FileWriter(FileName, true);
            PrintWriter writer = new PrintWriter(fw);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            //String FileName = "Output-Data" + formatter.format(date) +".txt";
            List<Short> spo2data = spO2.getData();
            List<Short> pulsedata = pulse.getData();
            List<String> datepoints = pulse.getDate();
            writer.println("Age: " + getAge());
            writer.println("------------------------------------------");
            writer.println("Limits: ");
            writer.println("Pulse: Lower: " + alarm.getLimits().getLowerPulse() +
                    ", Upper: " + alarm.getLimits().getUpperPulse());
            writer.println("SpO2: Lower: " + alarm.getLimits().getLowerSpO2() +
                    ", Upper: " + alarm.getLimits().getUpperSpO2());
            writer.println("------------------------------------------");
            for (int i = 0; i < pulse.getData().size(); i++) {
                Short pulseVal = pulsedata.get(i);
                String askPulse = "";
                if (pulseVal >= alarm.getLimits().getLowerPulse() && pulseVal <= alarm.getLimits().getUpperPulse()) {
                    askPulse = "Pulse not exceeded!";
                } else {
                    askPulse = "Pulse exceeded! Alarm!";
                }
                Short spo2Val = spo2data.get(i);
                String askSpO2 = "";
                if (spo2Val >= alarm.getLimits().getLowerSpO2() && spo2Val <= alarm.getLimits().getUpperSpO2()) {
                    askSpO2 = "SpO2 not exceeded!";
                } else {
                    askSpO2 = "SpO2 exceeded! Alarm!";
                }
                writer.println("Pulse: " + pulseVal +
                        ", " + "SpO2: " + spo2Val + ", " + askPulse +
                        ", " + askSpO2 + ", " + "time: " + pulse.getDate().get(i));
            }
            writer.close();
            return writer;
        } catch (IOException error) {
            JOptionPane.showConfirmDialog(null, error);
        }
        return null;
    }

    /**
     * Show latest data on GUI.
     * @param pulse signal
     * @param spO2  signal
     */
    private void newData(Short pulse, Short spO2) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                valuePulse.setText(Short.toString(pulse));
                valueSpo2.setText(Short.toString(spO2));
            }
        });
    }

    public static void main(String[] args) {

        // Instantiate the JFrame
        JFrame frame = new JFrame("PulsOxy");
        frame.setPreferredSize(new Dimension(800, 300));
        Visualization visu = new Visualization();
        frame.setContentPane(visu.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Connect with sensor
        RandomAccessFile pipe = null;
        try {
            pipe = new RandomAccessFile("\\\\.\\pipe\\ble_host_pipe", "r");
            System.out.println("Connected to sensor provider");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(true) {
            // try reading out the data from sensor
            try {
                String str = pipe.readLine();
                if (str == null)
                {
                    continue;
                }
                String[] strarray = str.split(" ");
                short[] data = new short[strarray.length];
                for(int i=0;i<strarray.length;i++)
                {
                    try
                    {
                        data[i] = Short.parseShort(strarray[i]);
                    }
                    catch(NumberFormatException ex)
                    {
                        break;
                    }
                }

                // processing the data, see sensors output information for more details
                // ID 10 for heart rate and SpO2 data
                if(data.length>=2 && data[1] == 10 && visu.isAgeSet())
                {
                    short latestPulse = data[4]; // data[4] contains pulse data
                    short latestSpO2 = data[5]; // data[5] contains SpO2 data
                    visu.pulse.append(latestPulse);
                    visu.spO2.append(latestSpO2);
                    visu.newData(latestPulse, latestSpO2);
                    visu.triggerAlarm(); // check the limits and trigger the alarm if needed
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
