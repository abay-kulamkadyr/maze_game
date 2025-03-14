import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * The GameScreen runs the game and has the logic to play the game.
 *
 * <p>This keeps track of all the internal game logic due to the fact that we cannot access the
 * pixel values. This also does all the drawing using the Slick2D library.
 *
 * @see BasicGameState
 * @author Group 19
 * @version 1.0
 */
public class GameScreen extends BasicGameState {
  public static boolean playReward;
  public static boolean playBonus;
  public static boolean playStat;
  protected static boolean isPlaying = false;
  private static boolean playScully;
  private final float width = 1280;
  private final float height = 720;
  private final float topBuffer = 144;
  private final float tileSize = 32;
  private final float gridWidth = width / tileSize; // 40
  private final float gridHeight = (height - topBuffer) / tileSize; // 18
  // Switch out with TheGrid
  private final TheGrid theGrid =
      new TheGrid((int) gridWidth, (int) gridHeight, (int) topBuffer, (int) tileSize);
  Player player1;
  MovingEnemy enemy;
  // Int value that holds whether the left shift has been pressed, and the gnome image for testing
  int gnomePress;
  Image gnome;
  // barrier positions
  ArrayList<Tile> barrierList = new ArrayList<>();
  Scoreboard sb = Scoreboard.getInstance();
  private Image tile = null;
  private Image topWall = null;
  private Image bottomWall = null;
  private Image leftWall = null;
  private Image rightWall = null;
  private Image topRight = null;
  private Image topLeft = null;
  private Image bottomLeft = null;
  private Image bottomRight = null;
  private Image reward = null;
  private Image hatch = null;
  private Image ladder = null;
  private Image scoreboard = null;
  private Animation bonusreward = null;
  private Animation player = null;
  private SpriteSheet downSheet;
  private SpriteSheet leftSheet;
  private SpriteSheet upSheet;
  private SpriteSheet rightSheet;
  private Animation statEnemy;
  private Animation moveEnemy;
  private SpriteSheet skeleSheet;
  private SpriteSheet skeleLeftSheet;
  private char lastInput;
  private Music bgm;
  // reward position
  private boolean openExitDoor;
  private Sound pop;
  private Sound pickupEffect;
  private Sound bonusEffect;
  private Sound statEffect;
  private Sound scullyEffect;
  private Sound gnomed;
  private int gnomeCount;

  /** Creates a new game screen. */
  public GameScreen() {}

  /**
   * Uses the Slick2D BasicGameState to create the game. Init is used 1 time for intialization of
   * Images and positions of the rewards, stationary enemy, barriers and bonus rewards.
   *
   * @param gameContainer uses slick2D to get our current game container.
   * @param sbg has functionality for the StateBasedGame.
   * @throws SlickException throws any exceptions that come from drawing things onto the screen.
   */
  @Override
  public void init(GameContainer gameContainer, StateBasedGame sbg) throws SlickException {
    // slick stub function
    sb.secretResetMethod();
    StationaryEnemy.resetList();
    openExitDoor = false;
    tile = new Image("Tile.png");
    topWall = new Image("Top wall 1.png");
    bottomWall = new Image("Bottom wall 1.png");
    rightWall = new Image("Right wall 1.png");
    leftWall = new Image("Left wall 1.png");
    bottomLeft = new Image("Bottom Left Corner.png");
    bottomRight = new Image("Bottom right corner wall.png");
    topLeft = new Image("Left Top corner wall.png");
    topRight = new Image("Top Right Corner.png");
    // player = new Image("src/main/resources/joe.png");

    leftSheet = new SpriteSheet("leftSprite.png", 32, 32);
    downSheet = new SpriteSheet("downSprite.png", 17, 32);
    rightSheet = new SpriteSheet("rightSprite.png", 32, 32);
    upSheet = new SpriteSheet("upSprite.png", 17, 32);
    skeleSheet = new SpriteSheet("skeleSprite.png", 32, 32);
    SpriteSheet skullSheet = new SpriteSheet("skullSprite.png", 32, 32);
    skeleLeftSheet = new SpriteSheet("skeleSpriteLeft.png", 32, 32);
    SpriteSheet coinSheet = new SpriteSheet("coinSheet.png", 32, 32);
    moveEnemy = new Animation(skeleSheet, 240);
    statEnemy = new Animation(skullSheet, 240);
    player = new Animation(rightSheet, 240);
    bonusreward = new Animation(coinSheet, 240);
    player.stop();
    gnomePress = 0;
    gnomeCount = 0;

    hatch = new Image("hatch.png");
    ladder = new Image("sack.png");
    // The gnome image
    gnome = new Image("gnomed.png");
    createBarriers();
    reward = new Image("chest.png");
    createRewards();

    // bonusreward=new Image("src/main/resources/coin_1.png");

    scoreboard = new Image("scoreboard.png");

    // hardcoding the location of each stationary enemy.
    // statEnemy=new Image("src/main/resources/sapphirefire.png");
    createStationary();

    createBonusRewards();
    player1 = new Player(tileSize, tileSize + topBuffer);
    enemy = new MovingEnemy((tileSize * 38), ((tileSize * 16) + topBuffer));
    theGrid.resetBooleans();
    bgm = new Music("Aladdin.ogg");
    pop = new Sound("Grunt.ogg");
    pickupEffect = new Sound("coinPickup.ogg");
    bonusEffect = new Sound("bonusSound.ogg");
    statEffect = new Sound("statHit.ogg");
    scullyEffect = new Sound("wow.ogg");
    gnomed = new Sound("gnomed.ogg");
    playStat = false;
    playReward = false;
    playBonus = false;
    playScully = false;
  }

  /**
   * Deals with updates to the game for every frame update. The player and the enemy can both only
   * update every 4th tick or every 8th of a second real time. The tick will then reset after each
   * time it gets to 4. Update is also use to check when the game should move into the pause,
   * failure or victory states.
   *
   * @param gameContainer uses slick2D to get our current game container.
   * @param sbg has functionality for the StateBasedGame.
   * @throws SlickException throws any exceptions that come from drawing things onto the screen.
   */
  @Override
  public void update(GameContainer gameContainer, StateBasedGame sbg, int delta)
      throws SlickException {
    // Added in Pause menu input
    if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      sb.pause();
      if (bgm.playing()) {
        bgm.stop();
        isPlaying = true;
      }
      sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
    }
    // TODO: Create a stack of inputs to use for keeping track of all inputs, reset the stack after
    // each group of
    // ticks
    getInput();
    Tick.instance();
    if (Tick.getTick() > 4) {
      Tick.instance();
      Tick.resetTick();

      char enemyDir = enemy.updateEnemy(player1.getGridX(), player1.getGridY());
      if (enemyDir == 'l') {
        int frame = moveEnemy.getFrame();
        moveEnemy = new Animation(skeleLeftSheet, 240);
        if (frame < moveEnemy.getFrameCount() - 1) {
          frame += 1;
        } else {
          frame = 0;
        }
        moveEnemy.setCurrentFrame(frame);
      } else if (enemyDir == 'r') {
        int frame = moveEnemy.getFrame();
        moveEnemy = new Animation(skeleSheet, 240);
        if (frame < moveEnemy.getFrameCount() - 1) {
          frame += 1;
        } else {
          frame = 0;
        }
        moveEnemy.setCurrentFrame(frame);
      }
      TheGrid.setTrueTileAt(enemy.getXPosition(), enemy.getYPosition(), 3);

      updateGame(lastInput);

      TheGrid.setTrueTileAt(player1.getXPosition(), player1.getYPosition(), 2);
      if (enemy.getXPosition() == player1.getXPosition()
          && enemy.getYPosition() == player1.getYPosition()) {
        theGrid.collisionHandling(
            player1.getXPosition(),
            player1.getYPosition(),
            enemy.getXPosition(),
            enemy.getYPosition());
      }
      lastInput = '\0';
    }
    TheGrid.setTrueTileAt(enemy.getXPosition(), enemy.getYPosition(), 3);
    if (playReward) {
      playReward = false;
      pickupEffect.play();
    }
    if (playBonus) {
      playBonus = false;
      bonusEffect.play();
    }
    if (playStat) {
      playStat = false;
      statEffect.play();
    }
    if (playScully) {
      playScully = false;
      scullyEffect.play();
    }
    if (theGrid.isEnteredEnd() || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
      sb.pause();
      if (bgm.playing()) {
        bgm.stop();
        isPlaying = true;
      }
      pop.play();
      sbg.enterState(5);
    }
    if (theGrid.isHasEncountered()) {
      sb.pause();
      if (bgm.playing()) {
        bgm.stop();
        isPlaying = true;
      }
      scullyEffect.play();
      sbg.enterState(6);
    }
    if (sb.getScore() < 0) {
      sb.pause();
      if (bgm.playing()) {
        bgm.stop();
        isPlaying = true;
      }
      scullyEffect.play();
      sbg.enterState(6);
    }
  }

  private void getInput() {
    // TODO: change it so the keyboard presses are all variables
    if ((Keyboard.isKeyDown(Player.right))) {
      lastInput = 'r';
    } else if ((Keyboard.isKeyDown(Player.down))) {
      lastInput = 'd';
    } else if ((Keyboard.isKeyDown(Player.left))) {
      lastInput = 'l';
    } else if ((Keyboard.isKeyDown(Player.up))) {
      lastInput = 'u';
    }
  }

  /**
   * Handles the drawing of any items on the screen. This is done after update so it will draw the
   * updates that are made.
   *
   * @param gameContainer uses slick2D to get our current game container.
   * @param sbg has functionality for the StateBasedGame.
   * @throws SlickException throws any exceptions that come from drawing things onto the screen.
   */
  @Override
  public void render(GameContainer gameContainer, StateBasedGame sbg, Graphics graphics)
      throws SlickException {
    if (!bgm.playing() && !isPlaying) {
      bgm.loop();
    }
    drawBoard();
    drawBarriers();
    drawRewards();
    drawStationary();
    if (openExitDoor) {
      drawExitDoor();
    }
    drawBonusRewards();
    // draws the character and updates it on the grid
    player.draw(player1.getXPosition(), player1.getYPosition());

    moveEnemy.draw(enemy.getXPosition(), enemy.getYPosition());
    // Checks if keyboard has clicked left shift
    // Once it has, a value of 1 is assigned to variable
    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
      gnomePress = 1;
    }
    // Once variable value has changed, gnome appears
    if (gnomePress == 1) {
      gnome.draw();
      if (gnomeCount == 0) {
        gnomed.play();
        gnomeCount++;
      }
    }

    graphics.drawString("1", 310, 86);
    graphics.drawString("" + sb.getScore(), 630, 86);
    graphics.drawString("" + sb.getTime(), 950, 86);
  }

  /** Draws the exit door once it is unlocked. */
  private void drawExitDoor() {
    ladder.draw(38 * tileSize, (3 * tileSize) + topBuffer);
    theGrid.setTileAt(38, 3, 7);
  }

  /**
   * Fills the barrierList with barriers that are going to be drawn. These positions never change.
   * Then it adds them to the list for drawing.
   *
   * @see Tile
   */
  private void createBarriers() {
    // adding in tile positions

    // top left
    for (int x = 4; x <= 12; x++) {
      barrierList.add(new Tile(x, 4, 1));
    }
    for (int y = 4; y <= 12; y++) {
      barrierList.add(new Tile(4, y, 1));
    }
    for (int y = 13; y <= 16; y++) {
      barrierList.add(new Tile(9, y, 1));
    }
    // top right
    for (int x = 28; x <= 35; x++) {
      barrierList.add(new Tile(x, 13, 1));
    }
    for (int y = 6; y <= 13; y++) {
      barrierList.add(new Tile(35, y, 1));
    }
    for (int y = 1; y <= 4; y++) {
      barrierList.add(new Tile(30, y, 1));
    }
    // middle line
    for (int x = 6; x <= 35; x++) {
      if (x % 3 == 0) barrierList.add(new Tile(x, 9, 1));
    }
    for (Tile value : barrierList) {
      theGrid.setTileAt(value.x, value.y, 1);
    }
  }

  /**
   * Creates the rewards. All of these positions do not change. Then adds them to the list for
   * drawing.
   *
   * @see Reward
   */
  private void createRewards() {
    Reward.addToList(new Reward(7 * tileSize, 2 * tileSize + topBuffer));
    Reward.addToList(new Reward(7 * tileSize, 6 * tileSize + topBuffer));
    Reward.addToList(new Reward(13 * tileSize, 14 * tileSize + topBuffer));
    Reward.addToList(new Reward(2 * tileSize, 15 * tileSize + topBuffer));
    Reward.addToList(new Reward(26 * tileSize, 2 * tileSize + topBuffer));
    Reward.addToList(new Reward(33 * tileSize, 11 * tileSize + topBuffer));
    Reward.addToList(new Reward(37 * tileSize, 15 * tileSize + topBuffer));
    Reward.addToList(new Reward(20 * tileSize, 9 * tileSize + topBuffer));
    Reward.addToList(new Reward(17 * tileSize, 3 * tileSize + topBuffer));
    Reward.addToList(new Reward(27 * tileSize, 15 * tileSize + topBuffer));
    Reward.addToList(new Reward(10 * tileSize, 1 * tileSize + topBuffer));
    for (Reward r : Reward.getRewardList()) {
      TheGrid.setTrueTileAt(r.getX(), r.getY(), 5);
    }
  }

  private void createStationary() {
    StationaryEnemy.addToList(new StationaryEnemy(10 * tileSize, 2 * tileSize + topBuffer));
    StationaryEnemy.addToList(new StationaryEnemy(2 * tileSize, 9 * tileSize + topBuffer));
    StationaryEnemy.addToList(new StationaryEnemy(6 * tileSize, 13 * tileSize + topBuffer));
    StationaryEnemy.addToList(new StationaryEnemy(19 * tileSize, 7 * tileSize + topBuffer));
    StationaryEnemy.addToList(new StationaryEnemy(19 * tileSize, 11 * tileSize + topBuffer));
    StationaryEnemy.addToList(new StationaryEnemy(21 * tileSize, 7 * tileSize + topBuffer));
    StationaryEnemy.addToList(new StationaryEnemy(21 * tileSize, 11 * tileSize + topBuffer));
    StationaryEnemy.addToList(new StationaryEnemy(19 * tileSize, 7 * tileSize + topBuffer));
    StationaryEnemy.addToList(new StationaryEnemy(33 * tileSize, 5 * tileSize + topBuffer));
    StationaryEnemy.addToList(new StationaryEnemy(30 * tileSize, 16 * tileSize + topBuffer));
    StationaryEnemy.addToList(new StationaryEnemy(37 * tileSize, 10 * tileSize + topBuffer));
    for (StationaryEnemy s : StationaryEnemy.getStationaryList()) {
      TheGrid.setTrueTileAt(s.getX(), s.getY(), 4);
    }
  }

  /**
   * Creates the bonus rewards. Each reward has a random location each time the game is run. The
   * while loop will run until all the rewards are made on valid spaces.
   *
   * @see BonusReward
   */
  private void createBonusRewards() {

    while (!BonusReward.isExceeding()) {
      BonusReward.addToList(new BonusReward((int) gridWidth - 1, (int) gridHeight - 1));
    }
  }

  /**
   * Draws the barrierList barriers onto the board and tells the grid that the tile it is on is of
   * type 1 for walls/barriers.
   */
  private void drawBarriers() {
    for (Tile value : barrierList) {
      topWall.draw(tileSize * value.x, tileSize * value.y + topBuffer, 2f);
      theGrid.setTileAt(value.x, value.y, 1);
    }
  }

  /**
   * Draws the stationary enemy. If the enemy has already been interacted with then it will not be
   * drawn and the tile it was it will be replaced with a blank tile. If it has not been interacted
   * with then it will draw and set the tile type to 4 for a stationary enemy.
   */
  private void drawStationary() {
    for (StationaryEnemy statEnemyIterator : StationaryEnemy.getStationaryList()) {
      {
        if (statEnemyIterator.isSetOnFire()) {

          TheGrid.setTrueTileAt(statEnemyIterator.getX(), statEnemyIterator.getY(), 0);
        } else {
          statEnemy.draw(statEnemyIterator.getX(), statEnemyIterator.getY());
          TheGrid.setTrueTileAt(statEnemyIterator.getX(), statEnemyIterator.getY(), 4);
        }
      }
    }
  }

  /**
   * Draws the rewards on the screen. If the reward has been collected already then that tile will
   * be set to blank and the count will go down by one. If the count gets to 0 then all rewards have
   * been collected and the exit door can be opened.
   *
   * @see Reward
   */
  private void drawRewards() {
    int count;
    count = Reward.getRewardListSize();
    for (Reward rwd : Reward.getRewardList()) {
      if (rwd.isCollected()) {
        TheGrid.setTrueTileAt(rwd.getX(), rwd.getY(), 0);
        count -= 1;
      } else {
        reward.draw(rwd.getX(), rwd.getY(), 2f);
        TheGrid.setTrueTileAt(rwd.getX(), rwd.getY(), 5);
      }
    }

    if (count <= 0) {
      openExitDoor = true;
    }
  }

  /**
   * Draws the Bonus Rewards on screen. If the reward has been collected the tile will just be set
   * to blank.
   *
   * <p>If it has not been collected first we will check if it should be on the board. Then it will
   * update based on if it's alive or not.
   *
   * <p>If it is alive then it will check if the time it has been alive for is longer than it's
   * allowed time to be alive. If that is also true then it will be taken off the screen, isAlive
   * will be switched to false, and it's state time will update. If it should still be alive then it
   * will be drawn and placed on theGrid.
   *
   * <p>If it is not alive then a check will be made to see if it has been dead long enough. If it
   * has been dead long enough then it will flip isAlive() and set the statetime to the current
   * time.
   *
   * @see BonusReward
   */
  private void drawBonusRewards() {

    for (BonusReward brwd : BonusReward.getBonusList()) {
      if (brwd.isCollected()) {
        TheGrid.setTrueTileAt(brwd.getPixelX(), brwd.getPixelY(), 0);

      } else {
        if (brwd.getIsAlive()) {

          Tick.instance();
          if (Tick.getTickRunning() - brwd.getStateTime() > brwd.getLifetime()) {
            brwd.setAlive(false);
            Tick.instance();
            brwd.setStateTime(Tick.getTickRunning());
          } else {
            int brwdX = brwd.getX();
            int brwdY = brwd.getY();
            theGrid.setTileAt(brwdX, brwdY, 6);
            bonusreward.draw(brwd.getPixelX(), brwd.getPixelY());
          }
        } else {
          Tick.instance();
          if (Tick.getTickRunning() - brwd.getStateTime() > brwd.getDeadTime()) {
            brwd.setAlive(true);
            Tick.instance();
            brwd.setStateTime(Tick.getTickRunning());
          }
        }
      }
    }
  }

  /**
   * Draws the starting game board. The board will have the edges set to walls and the insides will
   * be blank tiles. After that it will draw the starting hatch, and the scoreboard onto the screen.
   */
  private void drawBoard() {
    for (float x = 0; x < gridWidth; x++) {
      // TODO: Draw the corners by checking if we are in a corner
      if (x == 0) {
        for (float y = 0; y < gridHeight; y++) {
          if (y == 0) {
            topLeft.draw(tileSize * x, tileSize * y + topBuffer, 2f);
            theGrid.setTileAt(0, 0, 1);
          } else if (y == gridHeight - 1) {
            bottomLeft.draw(tileSize * x, tileSize * y + topBuffer, 2f);
            theGrid.setTileAt(0, (int) y, 1);
          } else {
            leftWall.draw(tileSize * x, tileSize * y + topBuffer, 2f);
            theGrid.setTileAt(0, (int) y, 1);
          }
        }
      } else if (x == gridWidth - 1) {
        for (float y = 0; y < gridHeight; y++) {
          if (y == 0) {
            topRight.draw(tileSize * x, tileSize * y + topBuffer, 2f);
            theGrid.setTileAt((int) x, 0, 1);
          } else if (y == gridHeight - 1) {
            bottomRight.draw(tileSize * x, tileSize * y + topBuffer, 2f);
            theGrid.setTileAt((int) x, (int) y, 1);
          } else {
            rightWall.draw(tileSize * x, tileSize * y + topBuffer, 2f);
            theGrid.setTileAt((int) x, (int) y, 1);
          }
        }
      } else {
        for (float y = 0; y < gridHeight; y++) {
          if (y == 0) {
            topWall.draw(tileSize * x, tileSize * y + topBuffer, 2f);
            theGrid.setTileAt((int) x, 0, 1);
          } else if (y == gridHeight - 1) {
            bottomWall.draw(tileSize * x, tileSize * y + topBuffer, 2f);
            theGrid.setTileAt((int) x, (int) y, 1);
          } else {
            tile.draw(tileSize * x, tileSize * y + topBuffer, 2f);
            theGrid.setTileAt((int) x, (int) y, 0);
          }
        }
      }
    }
    hatch.draw(tileSize, tileSize + topBuffer);
    scoreboard.draw();
  }

  /**
   * Returns the id of the state.
   *
   * @return the id of the state.
   */
  public int getID() {
    return 1;
  }

  /**
   * Function to update the game based on player input. Then it wil check if the player is moving
   * into a tile that is not a wall. After that if the tile is >2 then that means it will require
   * some kind of collision handling to happen before the player can move.
   *
   * @see Player
   * @see Tile
   * @param dir the direction in which the input correlates to?
   */
  public void updateGame(char dir) {

    if (dir == 'u') {
      if (TheGrid.getPixelType(player1.getXPosition(), player1.getYPosition() - tileSize) != 1) {
        if (TheGrid.getPixelType(player1.getXPosition(), player1.getYPosition() - tileSize) > 2) {
          theGrid.collisionHandling(
              player1.getXPosition(),
              player1.getYPosition() - tileSize,
              enemy.getXPosition(),
              enemy.getYPosition());
        }

        updatePlayerMovementAndAnimation(dir, upSheet);
      }
    } else if (dir == 'l') {
      if (TheGrid.getPixelType(player1.getXPosition() - tileSize, player1.getYPosition()) != 1) {
        if (TheGrid.getPixelType(player1.getXPosition() - tileSize, player1.getYPosition()) > 2) {
          theGrid.collisionHandling(
              player1.getXPosition() - tileSize,
              player1.getYPosition(),
              enemy.getXPosition(),
              enemy.getYPosition());
        }

        updatePlayerMovementAndAnimation(dir, leftSheet);
      }
    } else if (dir == 'd') {
      if (TheGrid.getPixelType(player1.getXPosition(), player1.getYPosition() + tileSize) != 1) {
        if (TheGrid.getPixelType(player1.getXPosition(), player1.getYPosition() + tileSize) > 2) {
          theGrid.collisionHandling(
              player1.getXPosition(),
              player1.getYPosition() + tileSize,
              enemy.getXPosition(),
              enemy.getYPosition());
        }
        updatePlayerMovementAndAnimation(dir, downSheet);
      }
    } else if (dir == 'r') {
      if (TheGrid.getPixelType(player1.getXPosition() + tileSize, player1.getYPosition()) != 1) {
        if (TheGrid.getPixelType(player1.getXPosition() + tileSize, player1.getYPosition()) > 2) {
          theGrid.collisionHandling(
              player1.getXPosition() + tileSize,
              player1.getYPosition(),
              enemy.getXPosition(),
              enemy.getYPosition());
        }
        updatePlayerMovementAndAnimation(dir, rightSheet);
      }
    } else {
      player.stop();
      player.restart();
    }
  }

  private void updatePlayerMovementAndAnimation(char dir, SpriteSheet upSheet) {
    player1.move(dir);

    int frame = player.getFrame();
    player = new Animation(upSheet, 240);
    if (frame < player.getFrameCount() - 1) {
      frame += 1;
    } else {
      frame = 0;
    }
    player.setCurrentFrame(frame);
    player.start();
  }
}
