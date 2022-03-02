package uk.ncl.pojo;

import java.util.ArrayList;
import java.util.List;

public class EcgData {
    //the middle value of chart
    public int middleNode = 100;
    //avNode fixed Height
    public int avNode = 70;
    //saNode fixed Height
    public int saNode1 = 20;
    public int saNode2 = 120;
    //Heart Rate can adjust 0-220
    private int frequency;
    //Heart Rate can adjust 0-550
    private int prInterval;
    //data set to input chart
    private ArrayList<Integer> dataSet = new ArrayList<Integer>();

    public ArrayList<Integer> getEcgSetData(){
        dataSet.clear();
       while (true){
           EcgSetData();
           if (dataSet.size()>50){
               dataSet.subList(0,50);
               break;
           }
       }
        return dataSet;
    }

    public void EcgSetData(){
        for (int i = 0; i< 27;i++){
            dataSet.add(middleNode);
        }
        dataSet.add(avNode);
        for (int i = 0; i< 20;i++){
            dataSet.add(middleNode);
        }
        dataSet.add(saNode1);
        dataSet.add(saNode2);
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setPrInterval(int prInterval) {
        this.prInterval = prInterval;
    }
}
