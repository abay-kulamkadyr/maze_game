import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BonusRewardTest
{
    BonusReward bonus;
    float  x = 2;
    float  y = 2;
    TheGrid theGrid = new TheGrid(100,100,0,32);
    @Before
    public void setup()
    {
        bonus = new BonusReward(38, 17);
        x = bonus.getX();
        y = bonus.getY();
    }

    @Test
    public void getDeadTime()
    {
        int t= bonus.getDeadtime();
        assertTrue(32<=t && t<=320);
    }

    @Test
    public void getLifeTime()
    {
        int k= bonus.getLifetime();
        assertTrue(32<=k && k<=320);
    }


    @Test
    public void getisAlive()
    {
        boolean l= bonus.getisAlive();
        assertEquals(false,l);
    }

    @Test
    public void setAlive()
    {
        bonus.setAlive(true);
    }

    @Test
    public void getStatetime()
    {
        int c=bonus.getStatetime();
        assertEquals(0,c);
    }

    @Test
    public void setStatetime()
    {
        bonus.setStatetime(0);
    }


    @Test
    public void collect()
    {
        bonus.collect();
        assertTrue(bonus.isCollected());
    }




    @Test
    public void getX()
    {
        float k = bonus.getX();
        assertEquals(x, k, 0.002);
    }

    @Test
    public void getY()
    {
        float j= bonus.getY();
        assertEquals(y,j,0.002);
    }

    @Test
    public void locationEquals()
    {
        float bonusX = bonus.getPixelX();
        float bonusY = bonus.getPixelY();
        boolean output1 = bonus.locationEquals(bonusX,bonusY);
        //Test the output
        assertEquals(true, output1);
    }

    @Test
    public void locationEqualsFalse()
    {
        boolean output1 = bonus.locationEquals((float)15,(float)10);
        //Test the output
        assertEquals(false, output1);
    }

    @Test
    public void isExceeding()
    {
        boolean output2 = bonus.isExceeding();
        //Test the output
        assertEquals(false, output2);
    }

    @Test
    public void isExceedingFalse()
    {
        BonusReward brd=new BonusReward(4,5);
        BonusReward brd2=new BonusReward(7,8);
        BonusReward brd3=new BonusReward(10,12);
        BonusReward brd4=new BonusReward(20,30);
        boolean output2 = bonus.isExceeding();
        //Test the output
        assertEquals(true, output2);
    }
    @Test
    public void bonusListTest(){
        BonusReward.addToList(bonus);
        BonusReward bonusFromList = BonusReward.getBonusList().get(0);
        assertEquals(bonus,bonusFromList);

    }
}