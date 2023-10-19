import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class RewardTest {
    Reward reward;
    float x = 2;
    float y = 2;

    @Before
    public void setup() {
        reward = new Reward(x, y);
        Reward.resetRewardList();

    }

    @Test
    public void collect() {
        reward.collect();
        assertTrue(reward.isCollected());
    }

    @Test
    public void getX() {
        float k = reward.getX();
        assertEquals(x, k, 0.0);
    }

    @Test
    public void getY() {
        float j = reward.getY();
        assertEquals(y, j, 0.0);
    }

    @Test
    public void locationEquals() {
        boolean output1 = reward.locationEquals(x, y);
        //Test the output
        assertTrue(output1);
    }

    @Test
    public void locationEqualsFalse() {
        boolean output2 = reward.locationEquals(10, 6);
        //Test the output
        assertFalse(output2);
    }

    @Test
    public void testRewardList() {
        Reward.addToList(reward);
        Reward rewardFromList = Reward.getRewardList().get(0);
        assertEquals(reward, rewardFromList);
    }

    @Test
    public void rewardListSize() {
        Reward.addToList(reward);
        assertEquals(1, Reward.getRewardListSize());
    }

}