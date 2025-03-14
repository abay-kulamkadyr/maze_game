import java.io.IOException;
import org.lwjgl.openal.AL;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/** Creates game and initilizes states that will be used */
public class RunGame extends StateBasedGame {
  public static final String gameName = "Nazareth";
  public static final int mainMenu = 0;
  public static final int gameScreen = 1;
  public static final int loreMenu = 2;
  public static final int settings = 3;
  public static final int pauseMenu = 4;
  public static final int victoryMenu = 5;
  public static final int failureMenu = 6;

  static {
    try {
      NativeLoader.loadNatives();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * Constructor for game and adds game states
   *
   * @param gameName contains name for game application
   */
  public RunGame(String gameName) {
    super(gameName);
    this.addState(new MainMenu());
    this.addState(new GameScreen());
    this.addState(new LoreMenu());
    this.addState(new Settings());
    this.addState(new Pause());
    this.addState(new VictoryMenu());
    this.addState(new FailureMenu());
  }

  public static void main(String[] args) {
    // window creation

    AppGameContainer appgc;
    try {
      // window creation
      appgc = new AppGameContainer(new RunGame(gameName));
      // screen size
      appgc.setDisplayMode(1280, 720, false);
      appgc.setTargetFrameRate(60);
      appgc.setShowFPS(false);
      new TickThread().start();
      appgc.start();
      AL.destroy();
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  // to create the main window, must be in the main method

  /**
   * Initializes the game states
   *
   * @param gameContainer for created game
   * @throws SlickException throwws for any error while initilizing
   */
  public void initStatesList(GameContainer gameContainer) throws SlickException {

    // initializing the states
    this.getState(mainMenu).init(gameContainer, this);
    this.getState(gameScreen).init(gameContainer, this);
    this.getState(loreMenu).init(gameContainer, this);
    this.getState(settings).init(gameContainer, this);
    this.getState(pauseMenu).init(gameContainer, this);
    this.getState(victoryMenu).init(gameContainer, this);
    this.getState(failureMenu).init(gameContainer, this);
    // first state that shows up right after running the program
    this.enterState(mainMenu);
  }
}
