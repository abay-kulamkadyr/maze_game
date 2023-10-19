import static org.junit.Assert.*;
import org.junit.Test;



public class ScoreboardTest {
    private Scoreboard sbTest=new Scoreboard();


    @Test
    public void score(){
        sbTest.setScore(100);
        sbTest.setScore(350);
        sbTest.setScore(700);
        sbTest.setScore(82);
        sbTest.setScore(-71);

        assertEquals(1161,sbTest.getScore());
    }


    @Test
    public void getTime(){
        long timeCheck= sbTest.getTime();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(timeCheck+1,sbTest.getTime());
    }

    @Test
    public void pauseResumeTest(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sbTest.pause();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sbTest.resume();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(3,sbTest.getTime());

    }

    @Test
    public void secretTimeResetTest(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sbTest.pause();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sbTest.resume();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sbTest.secretResetMethod();
        assertEquals(0,sbTest.getTime());
    }

    @Test
    public void secretScoreResetTest(){
        sbTest.setScore(20);
        sbTest.secretResetMethod();
        assertEquals(0,sbTest.getScore());
    }

    @Test
    public void createdSingleton(){
        Scoreboard singleTest=Scoreboard.getInstance();
        assertTrue(singleTest instanceof Scoreboard);

    }

}