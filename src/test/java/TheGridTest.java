import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class TheGridTest {
    TheGrid grid = new TheGrid(896,896,0,32);
    Scoreboard sb;
    @Before
    public void setUp() throws Exception {
        sb = Scoreboard.getInstance();
        grid.resetGrid();
        sb.secretResetMethod();
    }

    @Test
    public void setTileAt() {
        grid.setTileAt(10,10,2);
        assertEquals(2,grid.getTileAt(10,10));
    }

    @Test
    public void setTrueTileAt() {
        grid.setTrueTileAt(320,320,2);
        assertEquals(2,grid.getTileAt(10,10));
    }

    @Test
    public void getPixelType() {
        grid.setTileAt(25,25,5);
        assertEquals(5,grid.getPixelType(25*32,25*32));
    }

    @Test
    public void getTileAt() {
        grid.setTileAt(1,1,8);
        assertEquals(8,grid.getTileAt(1,1));
    }

    @Test
    public void getPixelTileAt() {
        grid.setTileAt(10,10,8);
        Tile tile = new Tile(10,10,8);
        Tile tile2 = grid.getPixelTileAt(320,320);
        boolean equals = tile.equals(tile2);
        assertTrue(equals);
    }

    //create scoreboard check the scores
    //check for enemy collisions
    @Test
    public void testRewardCollisions()
    {
        Reward.addToList(new Reward(32,32));
        grid.setTileAt(1,1,5);
        grid.collisionHandling(32,32,0,0);
        assertEquals(100,sb.getScore());
    }
    @Test
    public void testBonusRewardCollisions()
    {
        BonusReward bonusReward = new BonusReward(100,100);
        BonusReward.addToList(bonusReward);
        grid.setTileAt(bonusReward.getX(),bonusReward.getY(),6);
        grid.collisionHandling(bonusReward.getPixelX(),bonusReward.getPixelY(),0,0);
        assertEquals(500,sb.getScore());
    }
    @Test
    public void testDoorCollisions()
    {
        grid.setTileAt(10,10,7);
        grid.collisionHandling(320,320,0,0);
        assertTrue(grid.isEnteredEnd());
    }
    @Test
    public void testStationaryCollisions()
    {
        StationaryEnemy stat = new StationaryEnemy(416,416);
        StationaryEnemy.addToList(stat);
        grid.setTileAt(13,13,4);
        grid.collisionHandling(416,416,0,0);
        assertEquals(-300,sb.getScore());
    }
    @Test
    public void testMovingEnemyIntoPlayerCollisions()
    {
        grid.collisionHandling(448,448,448,448);
        assertTrue(grid.isHasEncountered());
    }
    @Test
    public void testMovingEnemyPlayerCollisions()
    {
        grid.setTileAt(15,15,3);
        grid.collisionHandling(480,480,0,0);
        assertTrue(grid.isHasEncountered());
    }
    @Test
    public void topBufferInit()
    {
        new TheGrid();
        assertEquals(0,TheGrid.topBuffer);
    }
    @Test
    public void testResetEncounter()
    {
        grid.encounter();
        boolean reset = grid.isHasEncountered();
        grid.resetBooleans();
        assertNotEquals(reset,grid.isHasEncountered());
    }
    @Test
    public void testResetEnterEnd()
    {
        grid.enterEnd();
        boolean reset = grid.isEnteredEnd();
        grid.resetBooleans();
        assertNotEquals(reset,grid.isEnteredEnd());
    }
}
