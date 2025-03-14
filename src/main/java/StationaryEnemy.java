import java.util.ArrayList;

/** The Stationary enemy takes away points from the player when they collide with it. */
public class StationaryEnemy {
  private static final ArrayList<StationaryEnemy> stationaryList = new ArrayList<>();
  private final float x;
  private final float y;
  public int punishment = -300;
  private boolean hasEntered = false;

  /**
   * Creates a new Stationary Enemy
   *
   * @param x the x coordinate in pixels.
   * @param y the y coordinate in pixels.
   */
  public StationaryEnemy(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Adds an object type StationaryEnemy to the stationary enemy list
   *
   * @param b StationaryEnemy
   */
  public static void addToList(StationaryEnemy b) {
    stationaryList.add(b);
  }

  /**
   * Get the stationary enemies list
   *
   * @return the stationary enemies list
   */
  public static ArrayList<StationaryEnemy> getStationaryList() {
    return stationaryList;
  }

  /** Removes all the elements from the stationary enemy list */
  public static void resetList() {
    stationaryList.clear();
  }

  /**
   * @return the punishment amount.
   */
  public int getPunishment() {
    return punishment;
  }

  /**
   * @return the x coordinate in pixels.
   */
  public float getX() {
    return x;
  }

  /**
   * @return the y coordinate in pixels.
   */
  public float getY() {
    return y;
  }

  /** Changes hasEntered to true on collision. */
  public void SetOnFire() {
    hasEntered = true;
  }

  /**
   * @return the value of hasEntered.
   */
  public boolean isSetOnFire() {
    return hasEntered;
  }

  /**
   * Compares the location of the stationary enemy to the coordinates given.
   *
   * @param x the x to check.
   * @param y the y to check.
   * @return the result of the comparison.
   */
  public boolean locationEquals(float x, float y) {
    return this.x == x && this.y == y;
  }
}
