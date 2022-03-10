package uk.ncl.pacemaker_ecg;

import uk.ncl.pojo.PacemakerModeData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
* pacemaker ECG
* */
public class PacemakerECGPanel extends JPanel {
    public PacemakerECGPanel(){}

    //x-axis displacement controlled by timer Action
    private final int TEP = 5;
    //inital x-axis value
    private int xValue = 100;
    private int count = 0;
    private int allCount = 50;

    //prepare the List to store x-axis data
    private List<Integer> x_axis = new ArrayList<Integer>();

    //prepare the List to store y-axis data
    private List<Integer> y_axis = new ArrayList<Integer>();

    private static ArrayList<Integer> temporary = new ArrayList<Integer>();

    public static boolean paint = false;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (paint){
            count = 0;
            xValue = 100;
            x_axis.clear();
            y_axis.clear();
        }
        //before every paint ecg chart clear entity panel
        g.clearRect(0,0,1200,150);


            g.setColor(Color.RED);

            if (xValue <= 1100) {
                x_axis.add(xValue);
                xValue += TEP;
            }

            y_axis.add(singleYData());


            if (xValue > 1100) {
                //the length of x_axis 1100;
                y_axis.remove(0);
            }

            //prepare Array to paint chart
            int[] x_array = new int[201];
            int[] y_array = new int[201];

            for (int i = 0; i < x_axis.size(); i++) {
                x_array[i] = x_axis.get(i);
            }
            for (int i = 0; i < y_axis.size(); i++) {
                y_array[i] = y_axis.get(i);
            }

            //draw the ecg chart
            g.drawPolyline(x_array, y_array, y_axis.size());

    }

    public int singleYData(){
        ArrayList<Integer> ecgSetData = PacemakerModeData.getEcgSetData();
        if (temporary.size() == ecgSetData.size()){
            if (count >= allCount){
                count = 0;
            }
            Integer data = temporary.get(count);
            count++;
            return data;
        }else {
            if (count < allCount){
                Integer data = temporary.get(count);
                count++;
                return data;
            }else{
                count = 0;
                allCount = PacemakerModeData.getCount();
                temporary.clear();
                temporary.addAll(ecgSetData);
                return temporary.get(count);
            }
        }
    }

    public static void initTemporary(){
        for (int i = 0; i< 27;i++){
            temporary.add(100);
        }
        temporary.add(70);
        for (int i = 0; i< 20;i++){
            temporary.add(100);
        }
        temporary.add(20);
        temporary.add(120);
    }

    public static void addData(){
        for (int i = 0; i< PacemakerModeData.frequency;i++){
            temporary.add(100);
        }
        temporary.add(PacemakerModeData.saNode);
        for (int i = 0; i< PacemakerModeData.prInterval;i++){
            temporary.add(100);
        }
        temporary.add(PacemakerModeData.avNode1);
        temporary.add(PacemakerModeData.avNode2);
    }



}
