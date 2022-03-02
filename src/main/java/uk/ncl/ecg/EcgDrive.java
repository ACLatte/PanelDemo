package uk.ncl.ecg;

import uk.ncl.pojo.EcgData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcgDrive {
    //just click left side green button can run this part project
    public static void main(String[] args) {
        EcgFrame ecgFrame = new EcgFrame("Pacemaker");
        ecgFrame.setVisible(true);
        ecgFrame.timerAction.start();
        ecgFrame.setDefaultCloseOperation(ecgFrame.EXIT_ON_CLOSE);
    }
}
