import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TickTest {

  @Before
  public void setup() {
    new TickThread().start();
  }

  @Test
  public void reset() {
    Tick.instance();
    Tick.resetTick();
    Tick.instance();
    assertEquals(0, Tick.getTick());
  }

  @Test
  public void timeElapsed() {
    try {
      Thread.sleep(32);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Tick.instance();
    assertEquals(1, Tick.getTick());
  }

  @Test
  public void increaseTick() {
    Tick.instance();
    int previous = Tick.getTick();
    Tick.instance();
    Tick.increaseTick();
    Tick.instance();
    assertEquals(1, Tick.getTick() - previous);
  }

  @Test
  public void increaseRunningTick() {
    Tick.instance();
    int previous = Tick.getTickRunning();
    Tick.instance();
    Tick.increaseTick();
    Tick.instance();
    assertEquals(Tick.getTickRunning(), previous + 1);
  }
}
