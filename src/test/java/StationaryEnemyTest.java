import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StationaryEnemyTest {
    StationaryEnemy stEnemy;
    @Before
    public void setUp() {
        stEnemy= new StationaryEnemy(5,6);
    }
    @Test
    public void PlayerEncountersEnemy()
    {
        //assume player is at 5,6
        int xPosPlayer=5;
        int yPosPlayer=6;
        stEnemy.SetOnFire();
        assertEquals(xPosPlayer,(int)stEnemy.getX());
        assertEquals(yPosPlayer,(int)stEnemy.getY());
        assertEquals (stEnemy.isSetOnFire(),stEnemy.locationEquals(xPosPlayer,yPosPlayer));
    }
    @Test
    public void PlayerDoesNotEncountersEnemy()
    {
        int xPosPlayer=6;
        int yPosPlayer=6;
        assertEquals (stEnemy.isSetOnFire(), stEnemy.locationEquals(xPosPlayer,yPosPlayer));
    }
    @Test
    public void getPunishmentTest()
    {
        assertEquals(-300,stEnemy.getPunishment());
    }

    @Test
    public void StationaryListTest()
    {
        StationaryEnemy.addToList(stEnemy);
        StationaryEnemy getElement= StationaryEnemy.getStationaryList().get(0);
        assertEquals(stEnemy, getElement);
    }
    @Test
    public void ResetListTest()
    {

        StationaryEnemy.resetList();
        assertEquals(0, StationaryEnemy.getStationaryList().size());
    }

}