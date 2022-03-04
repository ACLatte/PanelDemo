package uk.ncl.starter;
import uk.ncl.heart_ecg.HeartECGPanel;
import uk.ncl.pacemaker_ecg.PacemakerECGPanel;
import uk.ncl.pojo.HeartECGData;
import uk.ncl.pojo.PacemakerModeData;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 * @author YYDS
 * this class to do the integral software layout
 */
public class Entity_Layout {

    //integral container
    JFrame simulator =new JFrame("Simulator");

    private JLabel heartLabel = new JLabel("Heart Beat");
    //Heart ECG Chart create position
    private HeartECGPanel heartECG = new HeartECGPanel();

    //the slider and label of control heart rate
    private JLabel heartRate = new JLabel("Heart Rate:");
    private JSlider heartRateSlider = new JSlider(0, 220, 70);
    private JLabel heartRateLabel = new JLabel("70 bpm");

    //the slider of and label control PRInterval rate
    private JLabel prInterval = new JLabel("PRInterval:");
    private JSlider prIntervalSlider = new JSlider(0, 550, 150);
    private JLabel prIntervalLabel = new JLabel("150 ms");

    //the button to control heart chart start and stop
    private JButton heart_ON = new JButton("ON");
    private JButton heart_RESET = new JButton("RESET");

    //the button control pacemaker start and stop
    private JLabel pacemakerLabel = new JLabel("Pacemaker:");
    private JLabel pacemaker_Mode = new JLabel("OFF");

    //Pacemaker ECG Chart create position
    private PacemakerECGPanel pacemakerECG = new PacemakerECGPanel();

    //the button change different mode
    private JButton AAI = new JButton("AAI");
    private JButton VDD = new JButton("VDD");
    private JButton DDD = new JButton("DDD");
    private JButton mode_ON = new JButton("OFF");


    private JLabel batteryLabel = new JLabel("Battery Failure");
    //the slider control battery failure
    private JSlider batterySlider = new JSlider(0, 100, 100);
    private JLabel batteryPercentLabel = new JLabel("100%");
    private JButton batteryButton = new JButton("OFF");

    private JLabel leadsLabel = new JLabel("Leads Failure");
    //the checkbox control which leads failure
    private JCheckBox sa_nodeCheckBox = new JCheckBox("SA Node Failure");
    private JCheckBox av_nodeCheckBox = new JCheckBox("AV Node Failure");

    //control heart chart update immediately
    private Timer heartTimer = new Timer(100, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //this method can automatic call the Panel about paint method
            heartECG.repaint();
        }
    });

    //control pacemaker chart update immediately
    private Timer pacemakerTimer = new Timer(100, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //this method can automatic call the Panel about paint method
            pacemakerECG.repaint();
        }
    });

    //layout the integral layout
    public void initialLayout() {

        //simulator.setResizable(true);
        simulator.setSize(new Dimension(1200,600));
        //simulator.setLocationRelativeTo(null);
        simulator.setLayout(null);
        //setup location and add to frame
        heartLabel.setBounds(0,5,80,20);
        heartLabel.setFont(new Font("black",1 ,15));
        simulator.add(heartLabel);


        //Heart ECG Chart     0,30,1200,150
        heartECG.setBounds(0,30,1200,150);
        simulator.add(heartECG);


        heartRate.setBounds(0,192,70,20);
        simulator.add(heartRate);

        heartRateSlider.setBounds(75,185,400,40);
        simulator.add(heartRateSlider);
        heartRateSlider.setEnabled(false);

        heartRateLabel.setBounds(480,192,70,20);
        simulator.add(heartRateLabel);

        heart_ON.setBounds(550,187,80,30);
        simulator.add(heart_ON);

        prInterval.setBounds(0,237,70,20);
        simulator.add(prInterval);

        prIntervalSlider.setBounds(75,230,400,40);
        simulator.add(prIntervalSlider);
        prIntervalSlider.setEnabled(false);

        prIntervalLabel.setBounds(480,237,70,20);
        simulator.add(prIntervalLabel);

        heart_RESET.setBounds(550,232,80,30);
        simulator.add(heart_RESET);

        pacemakerLabel.setBounds(0,270,90,20);
        pacemakerLabel.setFont(new Font("black",1 ,15));
        simulator.add(pacemakerLabel);

        pacemaker_Mode.setBounds(90,277,40,10);
        simulator.add(pacemaker_Mode);


        //Pacemaker ECG Chart     0,295,1200,150
        pacemakerECG.setBounds(0,295,1200,150);
        simulator.add(pacemakerECG);


        AAI.setBounds(10,450,80,30);
        simulator.add(AAI);
        VDD.setBounds(100,450,80,30);
        simulator.add(VDD);
        DDD.setBounds(190,450,80,30);
        simulator.add(DDD);
        mode_ON.setBounds(280,450,80,30);
        simulator.add(mode_ON);

        batteryLabel.setBounds(0,485,120,20);
        batteryLabel.setFont(new Font("black",1 ,15));
        simulator.add(batteryLabel);
        batterySlider.setBounds(0,510,400,40);
        batterySlider.setEnabled(false);
        simulator.add(batterySlider);
        batteryPercentLabel.setBounds(405,517,40,20);
        simulator.add(batteryPercentLabel);
        batteryButton.setBounds(445,512,80,30);
        simulator.add(batteryButton);

        leadsLabel.setBounds(600,485,120,20);
        leadsLabel.setFont(new Font("black",1 ,15));
        simulator.add(leadsLabel);
        sa_nodeCheckBox.setBounds(600,510,200,20);
        simulator.add(sa_nodeCheckBox);
        av_nodeCheckBox.setBounds(600,530,200,20);
        simulator.add(av_nodeCheckBox);

        //All listeners below using for linkage

        //heart rate slider
        heartRateSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                heartRateLabel.setText(heartRateSlider.getValue()+" bpm");
                if (heartRateSlider.getValue() == 0){
                    HeartECGData.EcgDie();
                    HeartECGData.calculateheartRate(49);
                }else if (heartRateSlider.getValue() > 0 && heartRateSlider.getValue() <= 5){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(47);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(47);
                    }
                }else if (heartRateSlider.getValue() > 5 && heartRateSlider.getValue() <= 10){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(45);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(45);
                    }
                }else if (heartRateSlider.getValue() > 10 && heartRateSlider.getValue() <= 15){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(43);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(43);
                    }
                }else if (heartRateSlider.getValue() > 15 && heartRateSlider.getValue() <= 20){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(41);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(41);
                    }
                }else if (heartRateSlider.getValue() > 20 && heartRateSlider.getValue() <= 25){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(39);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(39);
                    }
                }else if (heartRateSlider.getValue() > 25 && heartRateSlider.getValue() <= 30){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(37);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(37);
                    }
                }else if (heartRateSlider.getValue() > 30 && heartRateSlider.getValue() <= 35){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(35);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(35);
                    }
                }else if (heartRateSlider.getValue() > 35 && heartRateSlider.getValue() <= 40){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(33);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(33);
                    }
                }else if (heartRateSlider.getValue() > 45 && heartRateSlider.getValue() <= 50){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(31);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(31);
                    }
                }else if (heartRateSlider.getValue() > 55 && heartRateSlider.getValue() <= 60){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(29);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(29);
                    }
                }else if (heartRateSlider.getValue() >65 && heartRateSlider.getValue() <= 70){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(27);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(27);
                    }
                }else if (heartRateSlider.getValue() >70 && heartRateSlider.getValue() <= 80){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(26);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(26);
                    }
                }else if (heartRateSlider.getValue() >80 && heartRateSlider.getValue() <= 90){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(25);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(25);
                    }
                }else if (heartRateSlider.getValue() >90 && heartRateSlider.getValue() <= 100){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(24);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(24);
                    }
                }else if (heartRateSlider.getValue() >100 && heartRateSlider.getValue() <= 110){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(23);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(23);
                    }
                }else if (heartRateSlider.getValue() >110 && heartRateSlider.getValue() <= 120){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(22);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(22);
                    }
                }else if (heartRateSlider.getValue() >120 && heartRateSlider.getValue() <= 130){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(21);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(21);
                    }
                }else if (heartRateSlider.getValue() >130 && heartRateSlider.getValue() <= 140){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(20);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(20);
                    }
                }else if (heartRateSlider.getValue() >140 && heartRateSlider.getValue() <= 150){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(19);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(19);
                    }
                }else if (heartRateSlider.getValue() >150 && heartRateSlider.getValue() <= 160){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(18);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(18);
                    }
                }else if (heartRateSlider.getValue() >160 && heartRateSlider.getValue() <= 170){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(17);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(17);
                    }
                }else if (heartRateSlider.getValue() >170 && heartRateSlider.getValue() <= 180){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(16);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(16);
                    }
                }else if (heartRateSlider.getValue() >180 && heartRateSlider.getValue() <= 190){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(15);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(15);
                    }
                }else if (heartRateSlider.getValue() >190 && heartRateSlider.getValue() <= 200){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(14);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(14);
                    }
                }else if (heartRateSlider.getValue() >200 && heartRateSlider.getValue() <= 210){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(13);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(13);
                    }
                }else if (heartRateSlider.getValue() >210 && heartRateSlider.getValue() <= 220){
                    HeartECGData.EcgRevive();
                    HeartECGData.calculateheartRate(12);
                    if (pacemaker_Mode.getText() == "DDD"){
                        PacemakerModeData.calculateheartRate(12);
                    }
                }
            }
        });

        heart_ON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (heart_ON.getText() == "ON"){
                    //add the timer into the Heart ECG chart
                    heartTimer.start();
                    heartRateSlider.setEnabled(true);
                    prIntervalSlider.setEnabled(true);
                    heart_ON.setText("OFF");
                }else if (heart_ON.getText() == "OFF"){
                    heartTimer.stop();
                    heartRateSlider.setValue(70);
                    heartRateSlider.setEnabled(false);
                    prIntervalSlider.setValue(150);
                    prIntervalSlider.setEnabled(false);
                    heart_ON.setText("ON");
                }
            }
        });

        //PR Interval Slider
        prIntervalSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                prIntervalLabel.setText(prIntervalSlider.getValue()+" ms");
                if (prIntervalSlider.getValue() > 0 && prIntervalSlider.getValue() <= 5){
                    HeartECGData.calculateprInterval(2);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(2);
                    }
                }else if (prIntervalSlider.getValue() > 5 && prIntervalSlider.getValue() <= 10){
                    HeartECGData.calculateprInterval(3);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(3);
                    }
                }else if (prIntervalSlider.getValue() > 10 && prIntervalSlider.getValue() <= 15){
                    HeartECGData.calculateprInterval(4);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(4);
                    }
                }else if (prIntervalSlider.getValue() > 15 && prIntervalSlider.getValue() <= 20){
                    HeartECGData.calculateprInterval(5);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(5);
                    }
                }else if (prIntervalSlider.getValue() > 20 && prIntervalSlider.getValue() <= 25){
                    HeartECGData.calculateprInterval(6);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(6);
                    }
                }else if (prIntervalSlider.getValue() > 25 && prIntervalSlider.getValue() <= 30){
                    HeartECGData.calculateprInterval(7);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(7);
                    }
                }else if (prIntervalSlider.getValue() > 30 && prIntervalSlider.getValue() <= 35){
                    HeartECGData.calculateprInterval(8);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(8);
                    }
                }else if (prIntervalSlider.getValue() > 35 && prIntervalSlider.getValue() <= 40){
                    HeartECGData.calculateprInterval(9);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(9);
                    }
                }else if (prIntervalSlider.getValue() > 40 && prIntervalSlider.getValue() <= 50){
                    HeartECGData.calculateprInterval(10);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(10);
                    }
                }else if (prIntervalSlider.getValue() > 50 && prIntervalSlider.getValue() <= 60){
                    HeartECGData.calculateprInterval(11);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(11);
                    }
                }else if (prIntervalSlider.getValue() > 60 && prIntervalSlider.getValue() <= 70){
                    HeartECGData.calculateprInterval(12);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(12);
                    }
                }else if (prIntervalSlider.getValue() > 70 && prIntervalSlider.getValue() <= 80){
                    HeartECGData.calculateprInterval(13);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(13);
                    }
                }else if (prIntervalSlider.getValue() > 80 && prIntervalSlider.getValue() <= 90){
                    HeartECGData.calculateprInterval(14);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(14);
                    }
                }else if (prIntervalSlider.getValue() > 90 && prIntervalSlider.getValue() <= 100){
                    HeartECGData.calculateprInterval(15);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(15);
                    }
                }else if (prIntervalSlider.getValue() > 100 && prIntervalSlider.getValue() <= 110){
                    HeartECGData.calculateprInterval(16);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(16);
                    }
                }else if (prIntervalSlider.getValue() > 110 && prIntervalSlider.getValue() <= 120){
                    HeartECGData.calculateprInterval(17);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(17);
                    }
                }else if (prIntervalSlider.getValue() > 120 && prIntervalSlider.getValue() <= 130){
                    HeartECGData.calculateprInterval(18);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(18);
                    }
                }else if (prIntervalSlider.getValue() > 130 && prIntervalSlider.getValue() <= 140){
                    HeartECGData.calculateprInterval(19);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(19);
                    }
                }else if (prIntervalSlider.getValue() >140 && prIntervalSlider.getValue() <= 150){
                    HeartECGData.calculateprInterval(20);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(20);
                    }
                }else if (prIntervalSlider.getValue() >150 && prIntervalSlider.getValue() <= 170){
                    HeartECGData.calculateprInterval(23);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(23);
                    }
                }else if (prIntervalSlider.getValue() >170 && prIntervalSlider.getValue() <= 190){
                    HeartECGData.calculateprInterval(24);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(24);
                    }
                }else if (prIntervalSlider.getValue() >190 && prIntervalSlider.getValue() <= 210){
                    HeartECGData.calculateprInterval(25);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(25);
                    }
                }else if (prIntervalSlider.getValue() >210 && prIntervalSlider.getValue() <= 230){
                    HeartECGData.calculateprInterval(26);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(26);
                    }
                }else if (prIntervalSlider.getValue() >230 && prIntervalSlider.getValue() <= 250){
                    HeartECGData.calculateprInterval(27);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(27);
                    }
                }else if (prIntervalSlider.getValue() >250 && prIntervalSlider.getValue() <= 270){
                    HeartECGData.calculateprInterval(28);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(28);
                    }
                }else if (prIntervalSlider.getValue() >270 && prIntervalSlider.getValue() <= 290){
                    HeartECGData.calculateprInterval(29);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(29);
                    }
                }else if (prIntervalSlider.getValue() >310 && prIntervalSlider.getValue() <= 330){
                    HeartECGData.calculateprInterval(30);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(30);
                    }
                }else if (prIntervalSlider.getValue() >330 && prIntervalSlider.getValue() <= 350){
                    HeartECGData.calculateprInterval(31);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(31);
                    }
                }else if (prIntervalSlider.getValue() >350 && prIntervalSlider.getValue() <= 400){
                    HeartECGData.calculateprInterval(32);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(32);
                    }
                }else if (prIntervalSlider.getValue() >400 && prIntervalSlider.getValue() <= 450){
                    HeartECGData.calculateprInterval(33);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(33);
                    }
                }else if (prIntervalSlider.getValue() >450 && prIntervalSlider.getValue() <= 500){
                    HeartECGData.calculateprInterval(34);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(34);
                    }
                }else if (prIntervalSlider.getValue() >500 && prIntervalSlider.getValue() <= 550){
                    HeartECGData.calculateprInterval(35);
                    if (pacemaker_Mode.getText() == "VDD"){
                        PacemakerModeData.calculateprInterval(35);
                    }
                }
            }
        });

        heart_RESET.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heartRateSlider.setValue(70);
                prIntervalSlider.setValue(150);
            }
        });


        AAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacemakerModeData.calculateheartRate(27);
                PacemakerModeData.calculateprInterval(20);
                pacemakerTimer.start();
                if (mode_ON.getText() != "OFF"){
                    pacemaker_Mode.setText("AAI");
                }else if (mode_ON.getText() == "OFF"){
                    pacemakerTimer.stop();
                }
            }
        });

        VDD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacemakerModeData.calculateheartRate(27);
                PacemakerModeData.calculateprInterval(20);
                pacemakerTimer.start();
                if (mode_ON.getText() != "OFF") {
                    pacemaker_Mode.setText("VDD");
                }else if (mode_ON.getText() == "OFF"){
                    pacemakerTimer.stop();
                }
            }
        });

        DDD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacemakerModeData.calculateheartRate(27);
                PacemakerModeData.calculateprInterval(20);
                pacemakerTimer.start();
                if (mode_ON.getText() != "OFF") {
                    pacemaker_Mode.setText("DDD");
                }else if (mode_ON.getText() == "OFF"){
                    pacemakerTimer.stop();
                }
            }
        });

        mode_ON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mode_ON.getText() == "OFF"){
                    mode_ON.setText("ON");
                    pacemaker_Mode.setText("ON");
                }else{
                    pacemakerTimer.stop();
                    mode_ON.setText("OFF");
                    pacemaker_Mode.setText("OFF");
                }
            }
        });

        batteryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (batteryButton.getText() == "OFF"){
                    batterySlider.setEnabled(true);
                    batteryButton.setText("ON");
                }else {
                    batterySlider.setValue(100);
                    batterySlider.setEnabled(false);
                    batteryButton.setText("OFF");
                }
            }
        });

        batterySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (batteryButton.getText() == "ON") {
                    batteryPercentLabel.setText(batterySlider.getValue()+"%");
                }
                if (batterySlider.getValue() == 100){
                    PacemakerModeData.saNode = 70;
                    PacemakerModeData.avNode1 = 20;
                    PacemakerModeData.avNode2 = 120;
                    PacemakerECGPanel.addData();
                }else if (batterySlider.getValue() > 90 && batterySlider.getValue() <= 100){
                    PacemakerModeData.saNode = 73;
                    PacemakerModeData.avNode1 = 28;
                    PacemakerModeData.avNode2 = 118;
                    PacemakerECGPanel.addData();
                }else if (batterySlider.getValue() > 80 && batterySlider.getValue() <= 90){
                    PacemakerModeData.saNode = 76;
                    PacemakerModeData.avNode1 = 36;
                    PacemakerModeData.avNode2 = 116;
                    PacemakerECGPanel.addData();
                }else if (batterySlider.getValue() > 70 && batterySlider.getValue() <= 80){
                    PacemakerModeData.saNode = 79;
                    PacemakerModeData.avNode1 = 42;
                    PacemakerModeData.avNode2 = 114;
                    PacemakerECGPanel.addData();
                }else if (batterySlider.getValue() > 60 && batterySlider.getValue() <= 70){
                    PacemakerModeData.saNode = 82;
                    PacemakerModeData.avNode1 = 50;
                    PacemakerModeData.avNode2 = 112;
                    PacemakerECGPanel.addData();
                }else if (batterySlider.getValue() > 50 && batterySlider.getValue() <= 60){
                    PacemakerModeData.saNode = 85;
                    PacemakerModeData.avNode1 = 58;
                    PacemakerModeData.avNode2 = 100;
                    PacemakerECGPanel.addData();
                }else if (batterySlider.getValue() > 40 && batterySlider.getValue() <= 50){
                    PacemakerModeData.saNode = 88;
                    PacemakerModeData.avNode1 = 66;
                    PacemakerModeData.avNode2 = 108;
                    PacemakerECGPanel.addData();
                }else if (batterySlider.getValue() > 30 && batterySlider.getValue() <= 40){
                    PacemakerModeData.saNode = 91;
                    PacemakerModeData.avNode1 = 74;
                    PacemakerModeData.avNode2 = 106;
                    PacemakerECGPanel.addData();
                }else if (batterySlider.getValue() > 20 && batterySlider.getValue() <= 30){
                    PacemakerModeData.saNode = 94;
                    PacemakerModeData.avNode1 = 82;
                    PacemakerModeData.avNode2 = 104;
                    PacemakerECGPanel.addData();
                }else if (batterySlider.getValue() > 10 && batterySlider.getValue() <= 20){
                    PacemakerModeData.saNode = 97;
                    PacemakerModeData.avNode1 = 90;
                    PacemakerModeData.avNode2 = 102;
                    PacemakerECGPanel.addData();
                }else if (batterySlider.getValue() >= 0 && batterySlider.getValue() <= 10){
                    PacemakerModeData.saNode = 100;
                    PacemakerModeData.avNode1 = 100;
                    PacemakerModeData.avNode2 = 100;
                    PacemakerECGPanel.addData();
                }
            }
        });

        sa_nodeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PacemakerModeData.saNode == 100){
                    PacemakerModeData.saNode = 70;
                }else {
                    PacemakerModeData.saNode = 100;
                }
                PacemakerECGPanel.addData();
            }
        });

        av_nodeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PacemakerModeData.avNode1 == 100){
                    PacemakerModeData.avNode1 = 20;
                    PacemakerModeData.avNode2 = 120;
                }else {
                    PacemakerModeData.avNode1 = 100;
                    PacemakerModeData.avNode2 = 100;
                }
                PacemakerECGPanel.addData();
            }
        });

        //setup frame visible
        simulator.setVisible(true);
        //setup clip top right X stop project
        simulator.setDefaultCloseOperation(3);
    }

}
