import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    Tile tile;
    @Before
    public void setup(){
        tile = new Tile(17,38,2);
    }
    @Test
    public void getSetTest()
    {
        tile.setType(10);
        assertEquals(10,tile.getType());
    }

    @Test
    public void locationEquals() {
        boolean equals = tile.locationEquals(17,38);
        assertTrue(equals);
    }

    @Test
    public void locationEqualsFalse() {
        boolean equals = tile.locationEquals(16,37);
        assertFalse(equals);
    }

    @Test
    public void testEquals() {
        Tile newTile = new Tile(17,38,2);
        boolean equals = (newTile.equals(tile));
        assertTrue(equals);
    }
    @Test
    public void testEqualsFalse()
    {
        Tile newTile = new Tile(18,39,3);
        boolean equals = (newTile.equals(tile));
        assertFalse(equals);
    }
}