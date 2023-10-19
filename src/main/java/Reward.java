import java.util.*;
import java.util.ArrayList;

/**
 * Reward class with functionality that would help in creating and drawing rewards in the GameScreen class.
 */
public class Reward
{
    public int value = 100;
    private float x;
    private float y;
    private boolean collected = false;
    private TheGrid theGrid = new TheGrid();
    private static ArrayList<Reward> rewardList = new ArrayList<>();


    /**
     * Non  default constructor to store the x and y values.
     * @param startX starting x coordinate.
     * @param startY strting y coordinate.
     */
    public Reward(float startX, float startY)
    {
        this.x = startX;
        this.y = startY;
    }
    /**
     * Changing the collected value to true once the reward is collected by the player.
     */
    public void collect()
    {
        collected = true;
    }

    /**
      returns true if  the reward is collected and false otherwise.
     */
    public boolean isCollected() {
        return collected;
    }

    /**
     * Getter for the x coordinate
     * @return the x coordinate.
     */
    public float getX()
    {
        return x;
    }

    /**
     * Getter for the y coordinate
     * @return the y coordinate.
     */
    public float getY()
    {
        return y;
    }

    /**
       * returns true if the x and the y coordinate of the reward matches the x and y coordinate of the coordinated inputted from the parameter
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
     * Add an object of type Reward to the reward list
     * @param b Reward
     */

    public static void addToList(Reward b)
    {
        rewardList.add(b);
    }

    /**
     * Get the current size of the reward list
     * @return value of the total number of rewards
     */
    public static int getRewardListSize()
    {
        return rewardList.size();
    }

    /**
     * Get the rewards list
     * @return the rewards list
     */
    public static ArrayList<Reward> getRewardList()
    {
        return rewardList;
    }
    /**
     *Removes all the elements from the reward list
     */
    public static void resetRewardList()
    {
        rewardList.clear();
    }

}