/**
 * The interface for all other moving characters to be based on.
 */

public interface Character {
    /**
     * This will update the X and Y cooridinates interally to move the character
     * @param dir this direction will decide how x and y change
     */
    public void move(char dir);

    /**
     * Gets the X position of the character
     * @return the X position of the character
     */
    public float getXPosition();

    /**
     * Gets the Y position of the character
     * @return the Y position of the character
     */
    public float getYPosition();
    /**
     * Sets the X coordinate of the player
     * @param x set the X coordinate to x
     */
    public void setXPosition(float x);

    /**
     * Sets the Y coordinate of the player
     * @param y set the Y coordinate to y
     */
    public void setYPosition(float y);
}
