import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.Random;

/** Moving enemy Class implements the Character interface */
public class MovingEnemy implements Character {
  public String availableMoveS = "udlr";
  public ArrayList<java.lang.Character> availableMovesConvert = new ArrayList<>();
  float x;
  float y;

  /**
   * @param startX Set the initial X coordinate of a new instance of MovingEnemy
   * @param startY Set the initial Y coordinate of a new instance of MovingEnemy
   */
  public MovingEnemy(float startX, float startY) {
    this.x = startX;
    this.y = startY;
    for (char ch : availableMoveS.toCharArray()) {
      availableMovesConvert.add(ch);
    }
  }

  /**
   * @param dir this direction will decide how x and y change
   */
  public void move(char dir) {
    if (dir == 'r') {
      x = x + 32;
    } else if (dir == 'l') {
      x = x - 32;
    } else if (dir == 'u') {
      y = y - 32;
    } else if (dir == 'd') {
      y = y + 32;
    }
    TheGrid.setTrueTileAt(x, y, 3);
  }

  /**
   * @return returns current X position of the moving enemy
   */
  public float getXPosition() {
    return x;
  }

  /**
   * @param x set the X coordinate to x
   */
  public void setXPosition(float x) {
    this.x = x;
  }

  /**
   * @return returns current Y position of the moving enemy
   */
  public float getYPosition() {
    return y;
  }

  /**
   * @param y set the Y coordinate to y
   */
  public void setYPosition(float y) {
    this.y = y;
  }

  /**
   * @return returns a random move from the set of available movements
   */
  public char randomMove() {
    char result;
    Random rand = new Random();
    result = availableMovesConvert.get(rand.nextInt(availableMovesConvert.toArray().length));
    return result;
  }

  /**
   * @param move a character to remove from the set of available movements
   */
  public void removeAvailableMoves(char move) {
    this.availableMovesConvert.remove(new java.lang.Character(move));
  }

  /** resets to the initial set of available movements */
  public void resetMoves() {
    availableMovesConvert.clear();
    for (char ch : this.availableMoveS.toCharArray()) {
      this.availableMovesConvert.add(ch);
    }
  }

  /**
   * @param x x coordinates of the player's location
   * @param y y coordinates of the player's location
   * @return returns the shortest distance to the player
   */
  public int heuristic(int x, int y) {
    int xEnemy = (int) (this.x / 32);
    int yEnemy = (int) ((this.y - 144) / 32);
    return (abs(xEnemy - x) + abs(yEnemy - y));
  }

  /** A method for updating the position of the moving enemy in every 4 ticks. */
  public char updateEnemy(int xPosPlayer, int yPosPlayer) {
    if ((TheGrid.getPixelType(this.getXPosition(), this.getYPosition() - 32)) == 1) {
      this.removeAvailableMoves('u');
    }
    if (TheGrid.getPixelType(this.getXPosition() - 32, this.getYPosition()) == 1) {
      this.removeAvailableMoves('l');
    }
    if (TheGrid.getPixelType(this.getXPosition(), this.getYPosition() + 32) == 1) {
      this.removeAvailableMoves('d');
    }
    if (TheGrid.getPixelType(this.getXPosition() + 32, this.getYPosition()) == 1) {
      this.removeAvailableMoves('r');
    }
    char BestMove;
    ArrayList<Integer> heuristicValues = getIntegers(xPosPlayer, yPosPlayer);
    int min = heuristicValues.get(0);
    for (int j = 1; j < heuristicValues.size(); j++) {
      if (heuristicValues.get(j) < min) {
        min = heuristicValues.get(j);
      }
    }
    // set the movement based on the lowest heuristic value
    int index = heuristicValues.indexOf(min);
    BestMove = this.availableMovesConvert.get(index);
    this.move(BestMove);
    char dir = BestMove;
    this.resetMoves();
    return dir;
  }

  private ArrayList<Integer> getIntegers(int xPosPlayer, int yPosPlayer) {
    ArrayList<Integer> heuristicValues = new ArrayList<>(this.availableMovesConvert.size());
    for (java.lang.Character character : this.availableMovesConvert) {
      if (character == 'u') {
        // getting coordinates of the player and enemy on the board
        MovingEnemy newState = new MovingEnemy(this.getXPosition(), this.getYPosition() - 32);
        heuristicValues.add(newState.heuristic(xPosPlayer, yPosPlayer));

      } else if (character == 'l') {

        MovingEnemy newState = new MovingEnemy(this.getXPosition() - 32, this.getYPosition());
        heuristicValues.add(newState.heuristic(xPosPlayer, yPosPlayer));
      } else if (character == 'd') {

        MovingEnemy newState = new MovingEnemy(this.getXPosition(), this.getYPosition() + 32);
        heuristicValues.add(newState.heuristic(xPosPlayer, yPosPlayer));
      } else if (character == 'r') {
        MovingEnemy newState = new MovingEnemy(this.getXPosition() + 32, this.getYPosition());
        heuristicValues.add(newState.heuristic(xPosPlayer, yPosPlayer));
      }
    }
    return heuristicValues;
  }
}
