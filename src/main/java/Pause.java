import java.awt.*;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/** Creates the Pause menu for the Nazareth game. */
public class Pause extends BasicGameState {
  private static TrueTypeFont gothic;
  int xPos = 900 / 2;
  int yPos = 1100 / 2;
  Image exit;
  Image background;
  Image restart;
  Image back;
  long currentTime;
  int timeCatch;

  // Placeholder score and time
  Input input;
  Scoreboard sb = Scoreboard.getInstance();
  // Font for score and time
  Font font;

  /** Non default constructor for the Pause which takes in state as a parameter. */
  public Pause() {}

  /** returns the id of the Pause menu state */
  @Override
  public int getID() {
    return 4;
  }

  /**
   * Initializes all the resources for the Pause menu state like the background image,exit
   * button,restart button,back button and the times that creates the font type of Times New Roman
   */
  @Override
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    // Placeholder background image
    background = new Image("pauseBG.png");
    // Exit button
    exit = new Image("ExittoMain.png");
    // Restart button
    restart = new Image("Restart Level.png");
    back = new Image("Back.png");
    try {
      InputStream fontStream = FailureMenu.class.getResourceAsStream("/Gothic_Birthday_Cake.ttf");
      if (fontStream == null) {
        throw new IOException("Font resource not found");
      }
      font = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(24f);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
    gothic = new TrueTypeFont(font, true);
  }

  /**
   * Sets the font as Times New roman and draws the images of the graphics with Score and Time
   * encapsulated inside it. Draws exit,restart and back button.
   */
  @Override
  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    g.setFont(gothic);

    g.drawImage(background, 0, 0);
    exit.draw(xPos, yPos);
    restart.draw(xPos, yPos - 84);
    back.draw(xPos, yPos - 84 - 84);

    g.drawString("Score :       " + sb.getScore(), xPos, 240);
    g.drawString("Time :        " + currentTime, xPos, 240 + 64);
  }

  /**
   * Updates the game screen by specifying if exit button is clicked or the back button is clicked.
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
    int yRestart = 720 - (yPos);
    int yBack = 720 - (yPos - 84);

    if ((mouseX < xPos + 320 && mouseX > xPos) && (mouseY < yExit + 84 && mouseY > yExit)) {
      // If Exit button is clicked, will exit game
      if (input.isMousePressed(0)) {

        sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
      }
    }

    if ((mouseX < xPos + 320 && mouseX > xPos) && (mouseY < yRestart + 84 && mouseY > yRestart)) {
      // Return back to main menu
      if (input.isMousePressed(0)) {
        GameScreen.isPlaying = false;
        restartGame(sbg);
      }
    }

    if ((mouseX < xPos + 320 && mouseX > xPos) && (mouseY < yBack + 84 && mouseY > yBack)) {
      // Once back button is clicked
      // Calls for scoreboard to resume time counting
      // Resets timeCatch for next pause
      // Enters Game state
      if (input.isMousePressed(0)) {
        GameScreen.isPlaying = false;
        sb.resume();
        timeCatch = 0;
        sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
      }
    }
  }

  /** Restarts the game with the original set up. */
  public void restartGame(StateBasedGame sbg) throws SlickException {
    sbg.getState(1).init(sbg.getContainer(), sbg);
    sbg.enterState(1);
  }
}
