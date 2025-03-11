/** The interface for all other moving characters to be based on. */
public interface Character {
  /**
   * This will update the X and Y cooridinates interally to move the character
   *
   * @param dir this direction will decide how x and y change
   */
  void move(char dir);

  /**
   * Gets the X position of the character
   *
   * @return the X position of the character
   */
  float getXPosition();

  /**
   * Sets the X coordinate of the player
   *
   * @param x set the X coordinate to x
   */
  void setXPosition(float x);

  /**
   * Gets the Y position of the character
   *
   * @return the Y position of the character
   */
  float getYPosition();

  /**
   * Sets the Y coordinate of the player
   *
   * @param y set the Y coordinate to y
   */
  void setYPosition(float y);
}
