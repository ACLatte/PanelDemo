package uk.ncl.starter;

import uk.ncl.heart_ecg.HeartECGPanel;
import uk.ncl.pacemaker_ecg.PacemakerECGPanel;


public class EcgDrive {

    //just click left side green button can run this part project
    public static void main(String[] args) {
        PacemakerECGPanel.initTemporary();
        HeartECGPanel.initTemporary();
        //simulator object can get value from interface
        Entity_Layout simulator = new Entity_Layout();
        simulator.initialLayout();
    }
}
