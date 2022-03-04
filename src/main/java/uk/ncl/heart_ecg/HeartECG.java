package uk.ncl.heart_ecg;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HeartECG extends JFrame {

    HeartECGPanel heartEcgCanvas = new HeartECGPanel();

    public HeartECG(String title){
        super(title);
        this.setSize(1200,300);
        this.add(heartEcgCanvas);
    }

    //add action to the JFrame part that can every 50 millis to call the repaint to repaint EcgPanel
    public Timer timerAction = new Timer(100, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //this method can automatic call the Panel about paint method
            heartEcgCanvas.repaint();
        }
    });




}


