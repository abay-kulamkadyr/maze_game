import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TickTest  {

    @Before
    public void setup()
    {
        new TickThread().start();
    }
    @Test
    public void reset()
    {
        Tick.instance().resetTick();
        assertEquals(0,Tick.instance().getTick());
    }
    @Test
    public void timeElapsed()
    {
        try {
            Thread.sleep((long)32);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(Tick.instance().getTick(), 1);
    }
    @Test
    public void increaseTick()
    {
        int previous= Tick.instance().getTick();
        Tick.instance().increaseTick();
        assertEquals(Tick.instance().getTick()-previous, 1);
    }
    @Test
    public void increaseRunningTick()
    {
        int previous=Tick.instance().getTickRunning();
        Tick.instance().increaseTick();
        assertEquals(Tick.instance().getTickRunning(),previous+1);
    }
}