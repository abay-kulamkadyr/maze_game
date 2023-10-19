import java.util.ArrayList;
import java.util.Random;

/**
 * The Bonus Reward awards the player a certain value for obtaining it. This reward has a random location and time it
 * is avaliable for everytime the game is ran.
 */

public class BonusReward
{
    public int value=500;
    private float x;
    private float y;
    private boolean collected = false;
    private static int count=0;
    private int deadtime=0;
    private int lifetime=0;
    private boolean isAlive=false;
    private int statetime=0;
    private TheGrid theGrid = new TheGrid();
    private static ArrayList<BonusReward> bonusList = new ArrayList<>();
    /**
     * Dead time is the of time the award should be off of the screen for. This is measured in ticks.
     * @see Tick
     * @return returns the deadtime.
     */
    public int getDeadtime()
    {
        return deadtime;
    }

    /**
     * Life time is the of time the award should be off of the screen for. This is measured in ticks.
     * @see Tick
     * @return returns the lifetime.
     */
    public int getLifetime()
    {
        return lifetime;
    }

    /**
     * isAlive is the boolean value to tell the game screen if this reward should be on screen.
     * @return the value of isAlive
     */
     public boolean getisAlive()
     {
         return isAlive;
     }

    /**
     * Allows the game to flip is alive to be true or false based on the time.
     * @param alive the input that isAlive will be changed to.
     */
    public void setAlive(boolean alive)
    {
        isAlive = alive;
    }

    /**
     * statetime holds when the bonus reward switches isAlive from true to false.
     * @return the time in ticks that statetime holds
     */
    public int getStatetime()
    {
        return statetime;
    }

    /**
     * Sets the statetime to a new value.
     * @param statetime time in ticks to set statetime to.
     */
    public void setStatetime(int statetime)
    {
        this.statetime=statetime;
    }
    //Constructor

    /**
     * Constructs a new BonusReward at the startX and startY. Gives the reward a random deadtime and lifetime along
     * with creating a statetime using the current time in ticks.
     * @see Tick
     * @param upperBoundX
     * @param upperBoundY
     */
    public BonusReward(int upperBoundX, int upperBoundY )
    {
        count++;
        Random rand=new Random();
        int upperLimit =320;
        deadtime = rand.nextInt(upperLimit -32)+32;
        lifetime=rand.nextInt(upperLimit -32)+32;
        statetime = (Tick.instance().getTickRunning())/32;
        int tileType = 1;
        int randomX = 0;
        int randomY = 0;
        while(tileType > 0)
        {
            //random from 1 - upperBoundX
            randomX = rand.nextInt(upperBoundX-1)+1;
            //creates random values from 1-upperBoundY
            randomY = rand.nextInt(upperBoundY-1)+1;
            tileType = theGrid.getTileAt(randomX,randomY);

        }
        this.x = randomX;
        this.y = randomY;

    }

    /**
     * changes collected to true when the reward is collected.
     * @see Reward
     */
    public void collect()
    {
        collected = true;
    }

    /**
     * Returns if isCollected is true or not.
     * @return collected's value
     */
    public boolean isCollected()
    {
        return collected;
    }

    /**
     * Gets the x value of the reward.
     * @return the float x
     */
    public int getX()
    {
        return (int)x;
    }

    /**
     * Gets the y value of the reward.
     * @return the float y
     */
    public int getY()
    {
        return (int)y;
    }

    public float getPixelX()
    {
        return x * theGrid.tileSize;
    }
    public float getPixelY()
    {
        return (y*theGrid.tileSize)+ theGrid.topBuffer;
    }

    /**
     * checks if the reward is on the same location as the given coordinates. This is in pixels.
     * @param x the x value in pixels to check
     * @param y the y value in pixels to check
     * @return if the location is the same as the reward or not.
     */
    public boolean locationEquals(float x, float y)
    {
        if(this.getPixelX() == x && this.getPixelY() == y)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Checks if there is alreadu the maximum of bonus rewards allowed.
     * @return whether the maximum amount of bonus rewards have been made or not.
     */
   public  static boolean isExceeding()
   {
       if(count>4)
       {
           return true;
       }
       else
       {
           return false;
       }
   }

    /**
     * Add an object of type BonusReward to the bonus rewards list
     * @param b BonusReward
     */
   public static void addToList(BonusReward b)
   {
       bonusList.add(b);
   }

    /**
     * Gets the bonus rewards list
     * @return the bonus rewards list
     */
    public static ArrayList<BonusReward> getBonusList()
    {
        return bonusList;
    }
}

