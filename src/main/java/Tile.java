/**
 * The class for the tile.
 * Each int type for the tile can have 7 values
 * 0 = empty tile
 * 1 = wall
 * 2 = player on tile
 * 3 = moving enemy on tile
 * 4 = stationary enemy on tile
 * 5 = reward on tile
 * 6 = bonus reward on tile
 * 7 = end door on tile
 *
 */
public class Tile {
    //three types of tiles
    // 0 = empty tile
    // 1 = wall
    // 2 = player on tile
    // 3 = moving enemy on tile
    // 4 = stationary enemy  on tile
    // 5 = reward on tile
    // 6 = bonus reward
    // 7 = end door
    int type;
    int x;
    int y;

    /**
     * Creates a new tile with a type, and coordinates.
     * @param x the tile's x coordinate.
     * @param y the tile's y coordinate.
     * @param type the tile's type from 0-7.
     */
    public Tile(int x, int y,int type)
    {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    /**
     * Changes the type for this tile
     * @param type the new type to overwrite
     */

    public void setType(int type)
    {
        this.type = type;
    }

    /**
     * Gets the type for the tile
     * @return the int type for the tile
     */

    public int getType ()
    {
        return type;
    }


    /**
     * checks if a pixel location of something equals this tile.
     * @param x the x that needs to be checked.
     * @param y the y that needs to be checked.
     * @return returns if the coordinates are the same.
     */
    public boolean locationEquals(float x, float y)
    {
        if(this.x == x && this.y == y)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Compares the current tile to another tile.
     * @param t Tile to compare to.
     * @return returns if the 2 tiles are the same or not.
     */
    public boolean equals(Tile t)
    {
        if(t.getType() == type && t.x == x && t.y == y)
        {
            return true;
        }
        return false;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
