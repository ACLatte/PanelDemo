import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.ncl.starter.Entity_Layout;

/*
* using Fest-swing and Junit4 to test listener logic of Button, Label and Slider in the Entity_layout class
*
* but sometimes the robot which provided by the java awt did not click Button or slide Slider
* that will causing Junit-test running failed
*
* But running the test class several times shows that all the test methods work correctly
* So the event logic of the layout module's click-and-slide and tab content is fine
*
* */

public class testEntity_layout {
    private FrameFixture testLayout;

    @Before
    public void setUp() {
        testLayout = new FrameFixture(new Entity_Layout());
    }

    /*
    * test heart_ON Button
    * */
    @Test
    public void test1() {
        testLayout.button("heart_ON").requireText("ON");
        testLayout.button("heart_ON").click();
        testLayout.button("heart_ON").requireText("OFF");
    }

    /*
     * test heartRateLabel and heartRateSlider
     * */
    @Test
    public void test2() {
        testLayout.button("heart_ON").click();
        testLayout.label("heartRateLabel").requireText("70 bpm");
        testLayout.slider("heartRateSlider").slideToMaximum();
        testLayout.label("heartRateLabel").requireText("220 bpm");
    }

    /*
    * test prIntervalLabel and prIntervalSlider
    * */
    @Test
    public void test3() {
        testLayout.button("heart_ON").click();
        testLayout.label("prIntervalLabel").requireText("150 ms");
        testLayout.slider("prIntervalSlider").slideToMinimum();
        testLayout.label("prIntervalLabel").requireText("0 ms");
    }

    /**
     * test heart_RESET Button
     */
    @Test
    public void test4() {
        testLayout.button("heart_ON").click();
        testLayout.slider("heartRateSlider").slideToMaximum();
        testLayout.slider("prIntervalSlider").slideToMinimum();
        testLayout.label("heartRateLabel").requireText("220 bpm");
        testLayout.label("prIntervalLabel").requireText("0 ms");
        testLayout.button("heart_RESET").click();
        testLayout.label("heartRateLabel").requireText("70 bpm");
        testLayout.label("prIntervalLabel").requireText("150 ms");
    }

    /*
    * test AAI VDD DDD Button
    * */
    @Test
    public void test6() {
        testLayout.label("pacemaker_Mode").requireText("OFF");
        testLayout.button("mode_ON").click();
        testLayout.label("pacemaker_Mode").requireText("ON");
        testLayout.button("AAI").click();
        testLayout.label("pacemaker_Mode").requireText("AAI");
        testLayout.button("VDD").click();
        testLayout.label("pacemaker_Mode").requireText("VDD");
        testLayout.button("DDD").click();
        testLayout.label("pacemaker_Mode").requireText("DDD");
    }

    /*
    * test pacemaker_Pacing_SA_Satuts label in the AAI mode
    * */
    @Test
    public void test7() {
        testLayout.button("heart_ON").click();
        testLayout.button("mode_ON").click();
        testLayout.button("AAI").click();
        testLayout.label("pacemaker_Pacing_SA_Satuts").requireText("");
        testLayout.slider("heartRateSlider").slideTo(50);
        testLayout.label("pacemaker_Pacing_SA_Satuts").requireText("pacing");
        testLayout.slider("heartRateSlider").slideTo(200);
        testLayout.label("pacemaker_Pacing_SA_Satuts").requireText("Inhibit");
    }

    /*
     * test pacemaker_Pacing_AV_Satuts label in the VDD mode
     * */
    @Test
    public void test8() {
        testLayout.button("heart_ON").click();
        testLayout.button("mode_ON").click();
        testLayout.button("VDD").click();
        testLayout.label("pacemaker_Pacing_AV_Satuts").requireText("");
        testLayout.slider("prIntervalSlider").slideTo(50);
        testLayout.label("pacemaker_Pacing_AV_Satuts").requireText("Inhibit");
        testLayout.slider("prIntervalSlider").slideTo(500);
        testLayout.label("pacemaker_Pacing_AV_Satuts").requireText("pacing");
    }

    /*
     * test pacemaker_Pacing_SA_Satuts label and pacemaker_Pacing_AV_Satuts label in the DDD mode
     * */
    @Test
    public void test9() {
        testLayout.button("heart_ON").click();
        testLayout.button("mode_ON").click();
        testLayout.button("DDD").click();
        testLayout.label("pacemaker_Pacing_SA_Satuts").requireText("");
        testLayout.label("pacemaker_Pacing_AV_Satuts").requireText("");
        testLayout.slider("heartRateSlider").slideTo(50);
        testLayout.label("pacemaker_Pacing_SA_Satuts").requireText("pacing");
        testLayout.slider("heartRateSlider").slideTo(200);
        testLayout.label("pacemaker_Pacing_SA_Satuts").requireText("Inhibit");
        testLayout.slider("prIntervalSlider").slideTo(50);
        testLayout.label("pacemaker_Pacing_AV_Satuts").requireText("Inhibit");
        testLayout.slider("prIntervalSlider").slideTo(500);
        testLayout.label("pacemaker_Pacing_AV_Satuts").requireText("pacing");
    }

    /*
    * test batteryButton,batteryPercentLabel and batterySlider
    * */
    @Test
    public void test10() {
        testLayout.button("batteryButton").requireText("OFF");
        testLayout.button("batteryButton").click();
        testLayout.button("batteryButton").requireText("ON");
        testLayout.label("batteryPercentLabel").requireText("100%");
        testLayout.slider("batterySlider").slideTo(50);
        testLayout.label("batteryPercentLabel").requireText("50%");
    }

    @After
    public void tearDown() {
        testLayout.cleanUp();
    }
}
