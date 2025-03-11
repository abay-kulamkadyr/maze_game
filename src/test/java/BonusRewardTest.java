import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BonusRewardTest {
  BonusReward bonus;
  float x = 2;
  float y = 2;

  @Before
  public void setup() {
    bonus = new BonusReward(38, 17);
    x = bonus.getX();
    y = bonus.getY();
  }

  @Test
  public void getDeadTime() {
    int t = bonus.getDeadTime();
    assertTrue(32 <= t && t <= 320);
  }

  @Test
  public void getLifeTime() {
    int k = bonus.getLifetime();
    assertTrue(32 <= k && k <= 320);
  }

  @Test
  public void getIsAlive() {
    boolean l = bonus.getIsAlive();
    assertFalse(l);
  }

  @Test
  public void setAlive() {
    bonus.setAlive(true);
  }

  @Test
  public void getStatetime() {
    int c = bonus.getStateTime();
    assertEquals(0, c);
  }

  @Test
  public void setStatetime() {
    bonus.setStateTime(0);
  }

  @Test
  public void collect() {
    bonus.collect();
    assertTrue(bonus.isCollected());
  }

  @Test
  public void getX() {
    float k = bonus.getX();
    assertEquals(x, k, 0.002);
  }

  @Test
  public void getY() {
    float j = bonus.getY();
    assertEquals(y, j, 0.002);
  }

  @Test
  public void locationEquals() {
    float bonusX = bonus.getPixelX();
    float bonusY = bonus.getPixelY();
    boolean output1 = bonus.locationEquals(bonusX, bonusY);
    // Test the output
    assertTrue(output1);
  }

  @Test
  public void locationEqualsFalse() {
    boolean output1 = bonus.locationEquals((float) 15, (float) 10);
    // Test the output
    assertFalse(output1);
  }

  @Test
  public void isExceeding() {
    boolean output2 = BonusReward.isExceeding();
    // Test the output
    assertFalse(output2);
  }

  @Test
  public void isExceedingFalse() {
    new BonusReward(4, 5);
    new BonusReward(7, 8);
    new BonusReward(10, 12);
    new BonusReward(20, 30);
    boolean output2 = BonusReward.isExceeding();
    // Test the output
    assertTrue(output2);
  }

  @Test
  public void bonusListTest() {
    BonusReward.getBonusList().clear();
    BonusReward.addToList(bonus);
    BonusReward bonusFromList = BonusReward.getBonusList().get(0);
    assertEquals(bonus, bonusFromList);
  }
}
