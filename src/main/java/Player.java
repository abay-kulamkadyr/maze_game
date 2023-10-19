import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * The player that the user will control. Player hold's it's own location.
 */
public class Player implements Character{
    float x;
    float y;

    protected static int up=Keyboard.KEY_W;
    protected static int left=Keyboard.KEY_A;
    protected static int right=Keyboard.KEY_D;
    protected static int down=Keyboard.KEY_S;


    /**
     * Creates a new player and assigns it coordinates.
     * @param startX the x for the Player to be set to.
     * @param startY the y for the Player to be set to.
     */
    public Player(float startX, float startY)
    {
        this.x = startX;
        this.y = startY;
    }

    /**
     * Moves the player's coordinates based on the given direction.
     * @param dir this direction will decide how x and y change.
     */
    public void move(char dir)
    {
        if(dir == 'r')
        {
            x = x + 32;
        }
        else if(dir == 'l')
        {
            x = x -32;
        }
        else if(dir == 'u')
        {
            y = y - 32;
        }
        else if(dir == 'd')
        {
            y = y + 32;
        }
    }


    /**
     *
     * @return returns the x position.
     */
    public float getXPosition()
    {
        return x;
    }

    /**
     *
     * @return returns the y position.
     */
    public float getYPosition()
    {
        return y;
    }

    /**
     * Gets the x position in grid coordinate form not in the pixel value.
     * @return the int on the grid equivalent of the pixel value.
     * @see TheGrid
     */
    public int getGridX()
    {
        return ((int)x)/32;
    }
    /**
     * Gets the y position in grid coordinate form not in the pixel value.
     * @return the int on the grid equivalent of the pixel value.
     * @see TheGrid
     */
    public int getGridY()
    {
        return ((int)y-144)/32;
    }

    /**
     * sets the x coordinate to a new x.
     * @param x set the x coordinate to x
     */
    public void setXPosition(float x)
    {
        this.x = x;
    }

    /**
     * sets the y coordinate to a new y.
     * @param y set the x coordinate to y
     */
    public void setYPosition(float y)
    {
        this.y = y;
    }

    public static void setUpKey(int key){
        up=key;
    }

    public static void setDownKey(int key){
       down=key;
    }
    public static void setLeftKey(int key){
        left=key;
    }
    public static void setRightKey(int key){right=key;
    }

}
