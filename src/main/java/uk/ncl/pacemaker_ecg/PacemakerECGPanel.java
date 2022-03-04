package uk.ncl.pacemaker_ecg;

import uk.ncl.pojo.HeartECGData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PacemakerECGPanel extends JPanel {
    public PacemakerECGPanel(){}

    //x-axis displacement controlled by timer Action
    private final int TEP = 5;
    //inital x-axis value
    private int xValue = 100;
    private int count = -1;

    //prepare the List to store x-axis data
    private List<Integer> x_axis = new ArrayList<Integer>();

    //prepare the List to store y-axis data
    private List<Integer> y_axis = new ArrayList<Integer>();


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (count == 49){
            count = -1;
        }
        count++;
        //before every paint ecg chart clear entity panel
        g.clearRect(0,0,1200,150);
        g.setColor(Color.RED);

        if (xValue<=1100) {
            x_axis.add(xValue);
            xValue += TEP;
        }

        y_axis.add(singleYData());

        if (xValue > 1100){
            //the length of x_axis 1100;
            y_axis.remove(0);
        }

        //prepare Array to paint chart
        int[] x_array = new int[201];
        int[] y_array = new int[201];

       for (int i = 0; i<x_axis.size() ;i++ ){
           x_array[i] = x_axis.get(i);
       }
        for (int i = 0; i<y_axis.size() ;i++ ){
            y_array[i] = y_axis.get(i);
        }

        //draw the ecg chart
        g.drawPolyline(x_array,y_array,y_axis.size());

    }

    public int singleYData(){
        HeartECGData heartECGData = new HeartECGData();
        ArrayList<Integer> ecgSetData = heartECGData.getEcgSetData();
        Integer data1 = ecgSetData.get(count);
        return data1;
    }


}
