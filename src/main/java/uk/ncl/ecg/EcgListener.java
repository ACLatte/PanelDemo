package uk.ncl.ecg;

import uk.ncl.pojo.EcgData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcgListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        EcgData ecgData = new EcgData();
        ecgData.setFrequency(10);
        ecgData.setPrInterval(10);
    }
}
