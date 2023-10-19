import java.util.*;
import java.util.ArrayList;
import org.newdawn.slick.*;

/**
 * The grid holds where everything. This is done by making eahc part of the 2-D array as a tile. The grid is
 * converted from pixels to single ints to keep track of a 40x18 grid.
 */
public class TheGrid {
    private static ArrayList<ArrayList<Tile>> grid = new ArrayList<ArrayList<Tile>>();
    protected static int width;
    protected static int height;
    public static int topBuffer;
    public static int tileSize;
    private static boolean enteredEnd = false;
    private static boolean hasEncountered = false;
    Scoreboard sb=Scoreboard.getInstance();
    /**
     * Creates a 2-D grid of arraylists with the width and height
     * @param width the width of the grid
     * @param height the height of the grid
     */
    public TheGrid(int width, int height, int topBuffer, int tileSize) {
        if(grid != null)
        {

            if(width > 0 && height > 0)
            {
                this.width = width;
                this.height = height;
            }
            else
            {
                this.width = 1;
                this.height = 1;
            }
            this.topBuffer = topBuffer;
            this.tileSize = tileSize;
            for (int x = 0; x < this.width; x++) {
                grid.add(new ArrayList<>());
                for(int y=0;y<this.height;y++)
                {
                    grid.get(x).add(new Tile(x,y,0));
                }

            }
        }

    }
    public TheGrid()
    {
        if(grid == null)
        {

            if(width > 0 && height > 0)
            {
                this.width = width;
                this.height = height;
            }
            else
            {
                this.width = 40;
                this.height = 18;
            }
            this.topBuffer = 144;
            this.tileSize = 32;
            for (int x = 0; x < this.width; x++) {
                grid.add(new ArrayList<>());
                for(int y=0;y<this.height;y++)
                {
                    grid.get(x).add(new Tile(x,y,0));
                }

            }
        }
    }

    /**
     * Sets the tile to that type at the given coordinates.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param type the type to change the tile to.
     */
    public void setTileAt(int x, int y, int type)
    {
        grid.get(x).get(y).setType(type);
    }

    /**
     * Get the pixel coordinates and replace the given tile
     *
     * @param x    pixel coordinate for x
     * @param y    pixel coordinate for y
     * @param type set the tile type to this
     */
    public static void setTrueTileAt(float x, float y, int type)
    {
        if(x >= 0 && y >= 0)
        {
            int trueX = ((int)x)/tileSize   ;
            int trueY = ((int)y-topBuffer)/tileSize;
            grid.get(trueX).get(trueY).setType(type);
        }
    }

    /**
     * Input a pixel coordinate to get the type at the spot
     * @param x pixel x to be converted into the grid
     * @param y pixel y to be converted into the grid
     * @return return the type of the tile
     */
    public static int getPixelType(float x, float y)
    {
        int trueX = ((int)x)/tileSize;
        int trueY = ((int)y- topBuffer)/tileSize;
        return grid.get(trueX).get(trueY).getType();
    }

    /**
     * Gets the tile type at a given grid coordinate.
     * @param x int coordinate for x.
     * @param y int coordinate for y.
     * @return the tile type.
     */
    public static int getTileAt(int x, int y)
    {
        return grid.get(x).get(y).getType();
    }

    /**
     * Same as getTileAt but accepts pixel coordinates instead. Converts x by dividing out the single tile size.
     * Converts y by removing the topBuffer and then dividing out the single tile size.
     * @param x the x coordinate in pixels.
     * @param y the y coordinate in pixels.
     * @return returns the tile at the coordinate.
     */
    public Tile getPixelTileAt(float x, float y)
    {
        int trueX = ((int)x)/tileSize;
        int minus = this.topBuffer;
        int trueY = ((int)y - minus)/tileSize;
        return grid.get(trueX).get(trueY);
    }

    public boolean isEnteredEnd() {
        return enteredEnd;
    }

    public void enterEnd() {
        this.enteredEnd = true;
    }

    public boolean isHasEncountered() {
        return hasEncountered;
    }
    public void encounter()
    {
        this.hasEncountered = true;
    }


    /**
     * Handles any colisions with the player and a different tile type. Each collision is handled different based on
     * the type of collision that happens.
     * <p>
     *     If it is a 5 (normal reward), that reward will be tracked down as collected and the player's score will
     *     increase by 100.
     * </p>
     * <p>
     *     If it is a 6 (bonus reward), that reward will be collected and the player's score will increase by 500.
     * </p>
     * <p>
     *     If it is a 7 (the exit door), the value enteredEnd will become true so the victory screen can show up.
     * </p>
     * <p>
     *     If it is a 3 (enemy) or if the enemy enters a player tile, then the failure screen will trigger.
     * </p>
     * @param playerX location of the player's x.
     * @param playerY location of the player's y.
     * @param enemyX  location of the enemy's x.
     * @param enemyY  location of the enemy's y.
     * @see Scoreboard
     * @see Tile
     */
    public void collisionHandling(float playerX, float playerY, float enemyX, float enemyY)
    {
        Tile t = getPixelTileAt(playerX,playerY);
        //normal reward
        int x = t.getX();
        int y = t.getY();
        if(t.getType() == 5)
        {
            sb.setScore(100);
            GameScreen.playReward = true;
            for(Reward rwd : Reward.getRewardList())
            {
                if(rwd.locationEquals(playerX,playerY))
                {
                    rwd.collect();
                }
            }

        }
        //for bonus rewards
        if(t.getType() == 6)
        {

            sb.setScore(500);
            GameScreen.playBonus = true;
            for(BonusReward brwd : BonusReward.getBonusList())
            {
                if(brwd.locationEquals(playerX,playerY))
                {
                    brwd.collect();
                }
            }
        }

        //type 7 is end door
        if(t.getType() == 7)
        {
            enterEnd();
        }
        //stationary enemy
        if(t.getType()==4){
            GameScreen.playStat = true;
            for (StationaryEnemy stat:StationaryEnemy.getStationaryList())
            {
                if(stat.locationEquals(playerX,playerY))
                {
                    sb.setScore(stat.getPunishment());
                    stat.SetOnFire();
                }
            }
        }

        if ((playerX==enemyX && playerY== enemyY) || t.getType() == 3)
        {
            encounter();
        }

    }

    /**
     * Resets the booleans back to false so the game screen can be set back to the start.
     */
    public void resetBooleans() {
        hasEncountered = false;
        enteredEnd = false;
    }

    /**
     * Resets all the grid tiles back to 0 so it can be made again.
     */
    public void resetGrid()
    {
        for (int x = 0; x < this.width; x++) {
            for(int y=0;y<this.height;y++)
            {
                grid.get(x).get(y).setType(0);
            }

        }
    }


}
