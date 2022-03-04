package uk.ncl.pacemaker_ecg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PacemakerECG extends JFrame {

    PacemakerECGPanel pacemakerEcgPanel = new PacemakerECGPanel();

    public PacemakerECG(String title){
        super(title);
        this.setSize(1200,300);
        this.add(pacemakerEcgPanel);
    }

    //add action to the JFrame part that can every 50 millis to call the repaint to repaint EcgPanel
    public Timer timerAction = new Timer(50, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //this method can automatic call the Panel about paint method
            pacemakerEcgPanel.repaint();
        }
    });




}

