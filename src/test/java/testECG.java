import org.junit.Assert;
import org.junit.Test;
import uk.ncl.pojo.HeartECGData;
import uk.ncl.pojo.PacemakerModeData;

import java.util.ArrayList;

/*
* using Junit4 to test HeartECG data
* */
public class testECG {

    /*
    * test HeartECG data
    * */
    @Test
    public void testHeartECG() {
        ArrayList<Integer> ecgSetData = HeartECGData.getEcgSetData();
        int size = ecgSetData.size();
        Assert.assertTrue(ecgSetData.size() == HeartECGData.getCount());
        HeartECGData.calculateheartRate(30);
        HeartECGData.calculateCount();
        Assert.assertTrue(ecgSetData.size() == HeartECGData.getCount()-3);
        HeartECGData.calculateprInterval(23);
        HeartECGData.calculateCount();
        Assert.assertTrue(ecgSetData.size() == HeartECGData.getCount()-6);
        ArrayList<Integer> ecgSetData1 = HeartECGData.getEcgSetData();
        Assert.assertEquals(size,ecgSetData1.size()-6);
    }

    /*
     * test PacemakerECG data
     * */
    @Test
    public void testPacemakerECG() {
        ArrayList<Integer> ecgSetData = PacemakerModeData.getEcgSetData();
        int size = ecgSetData.size();
        Assert.assertTrue(ecgSetData.size() == PacemakerModeData.getCount());
        PacemakerModeData.calculateheartRate(30);
        PacemakerModeData.calculateCount();
        Assert.assertTrue(ecgSetData.size() == PacemakerModeData.getCount()-3);
        PacemakerModeData.calculateprInterval(23);
        PacemakerModeData.calculateCount();
        Assert.assertTrue(ecgSetData.size() == PacemakerModeData.getCount()-6);
        ArrayList<Integer> ecgSetData1 = PacemakerModeData.getEcgSetData();
        Assert.assertEquals(size,ecgSetData1.size()-6);
    }

}
