package uk.ncl.pojo;

import java.util.ArrayList;

/*
* pacemaker ECG data static Tools class
* */
public class PacemakerModeData {
    //the middle value of chart
    public static int middleNode = 100;
    //avNode fixed Height
    public static int saNode = 70;
    //saNode fixed Height
    public static int avNode1 = 20;
    public static int avNode2 = 120;
    //Heart Rate can adjust 0-220
    public static int frequency = 27;
    //Heart Rate can adjust 0-550
    public static int prInterval = 20;
    //count set sum
    private static int count = 50;
    //data set to input chart
    private static ArrayList<Integer> dataSet = new ArrayList<Integer>();

    public static ArrayList<Integer> getEcgSetData(){
        dataSet.clear();
        calculateCount();
        EcgSetData();
        return dataSet;
    }

    public static void EcgSetData(){
        for (int i = 0; i< frequency;i++){
            dataSet.add(middleNode);
        }
        dataSet.add(saNode);
        for (int i = 0; i< prInterval;i++){
            dataSet.add(middleNode);
        }
        dataSet.add(avNode1);
        dataSet.add(avNode2);
    }

    public static void calculateheartRate(int heartRate){
        frequency = heartRate;
    }

    public static void calculateprInterval(int printerval){
        prInterval = printerval;
    }

    public static void calculateCount(){
        count = 3+frequency+prInterval;
    }

    public static int getCount() {
        return count;
    }
}
