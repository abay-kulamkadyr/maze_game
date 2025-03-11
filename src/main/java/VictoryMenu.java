import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Creates a victory menu class that leads to the drawing and updating of the Victory Menu if the
 * player wins.
 */
public class VictoryMenu extends BasicGameState {
  private static TrueTypeFont gothic;
  int xPos = 900 / 2;
  int yPos = 1100 / 2;
  Image exit;
  Image background;
  Image exitToMain;
  Input input;
  // Placeholder score and time
  long currentTime;
  int timeCatch;
  // Font for score and time
  Font font;
  Scoreboard sb = Scoreboard.getInstance();

  /** Non default constructor to know the state of the VictoryMenu */
  public VictoryMenu() {}

  /** returns the ID of the VictoryMenu state. */
  @Override
  public int getID() {

    return 5;
  }

  /**
   * * Initializes all the resources for the victory menu like exit,restart,exitToMain.The times
   * which decides the font.
   *
   * @param gc Game container of the game
   * @param sbg state based game
   */
  @Override
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    // Placeholder background image
    background = new Image("src/main/resources/victoryBG.png");
    // Exit button
    exit = new Image("src/main/resources/Exit.png");
    // Restart button
    exitToMain = new Image("src/main/resources/ExittoMain.png");
    try {
      font =
          Font.createFont(
                  Font.TRUETYPE_FONT, new File("src/main/resources/Gothic_Birthday_Cake.ttf"))
              .deriveFont(24f);
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    gothic = new TrueTypeFont(font, true);
  }

  /**
   * Sets the font as Times New roman and draws the images of the graphics with Score and Time
   * encapsulated inside it. Draws the background image along with the the images of exit,restart
   * and exitToMain.
   */
  @Override
  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    g.setFont(gothic);

    g.drawImage(background, 0, 0);
    exit.draw(xPos, yPos);

    exitToMain.draw(xPos, yPos - 84);

    g.drawString("Score :        " + sb.getScore(), xPos - 7, (float) 720 / 2 - 48);
    g.drawString("Time :        " + currentTime, xPos, (((float) 720 / 2)));
  }

  /**
   * Updates the game screen by specifying if exit button is clicked or the restart button is
   * clicked.
   */
  @Override
  public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    input = gc.getInput();

    if (timeCatch < 1) {
      currentTime = sb.getTime();
      timeCatch++;
    }
    int mouseX = Mouse.getX();
    int mouseY = Mouse.getY();

    // Y values for exit and restart button
    int yExit = 720 - (yPos + 84);
    int yExitToMain = 720 - (yPos);

    if ((mouseX < xPos + 320 && mouseX > xPos) && (mouseY < yExit + 84 && mouseY > yExit)) {
      if (input.isMousePressed(0)) {
        System.exit(0);
      }
    }

    if ((mouseX < xPos + 320 && mouseX > xPos)
        && (mouseY < yExitToMain + 84 && mouseY > yExitToMain)) {
      if (input.isMousePressed(0)) {
        sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
      }
    }
  }
}
