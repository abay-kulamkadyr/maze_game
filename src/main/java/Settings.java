import java.awt.*;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Settings extends BasicGameState {

  private static TrueTypeFont gothic;
  Image back;
  Image up;
  Image down;
  Image left;
  Image right;
  Image bg;
  Image blank;
  int xPos = 900 / 2;
  int stallkey = -1;
  Font font;

  public Settings() {}

  @Override
  public int getID() {
    return 3;
  }

  /**
   * Intializes all the variables that will be needed for the menu.
   *
   */
  @Override
  public void init(GameContainer container, StateBasedGame game) throws SlickException {
    back = new Image("BackSmole.png");
    bg = new Image("SettingsBG.png");
    up = new Image("Up.png");
    down = new Image("Down.png");
    right = new Image("Right.png");
    left = new Image("Left.png");
    blank = new Image("Blank.png");

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
   * Renders in the graphics and displays which keys are being used for controls.
   *
   */
  @Override
  public void render(GameContainer container, StateBasedGame game, Graphics g)
      throws SlickException {
    g.setFont(gothic);
    g.setColor(Color.black);
    bg.draw();
    back.draw(16, 38);
    up.draw(xPos - 200, 200);
    down.draw(xPos - 200, 305);
    left.draw(xPos - 200, 410);
    right.draw(xPos - 200, 515);
    blank.draw(xPos + 200, 200);
    blank.draw(xPos + 200, 305);
    blank.draw(xPos + 200, 410);
    blank.draw(xPos + 200, 515);
    drawUp(Keyboard.getKeyName(Player.up));
    drawDown(Keyboard.getKeyName(Player.down));
    drawRight(Keyboard.getKeyName(Player.right));
    drawLeft(Keyboard.getKeyName(Player.left));
  }

  private void drawDown(String s) {
    gothic.drawString(xPos + 350, 320, s, Color.black);
  }

  private void drawUp(String s) {
    gothic.drawString(xPos + 350, 215, s, Color.black);
  }

  private void drawLeft(String s) {
    gothic.drawString(xPos + 350, 425, s, Color.black);
  }

  private void drawRight(String s) {
    gothic.drawString(xPos + 350, 530, s, Color.black);
  }

  /**
   * Updates the menu and allows the user to change key bindings. To do this they must first press a
   * key to change the button to then click on the key that they want to change.
   *
   */
  @Override
  public void update(GameContainer container, StateBasedGame stb, int delta) throws SlickException {
    Input input = container.getInput();
    int mouseX = Mouse.getX();
    int mouseY = Mouse.getY();
    int yBack = 720 - 38;
    int xBack = 16;
    int xKey = xPos + 200;
    int yUp = 520;
    int yRight = 205;
    int yDown = 415;
    int yLeft = 310;
    if ((mouseX < xBack + 149 && mouseX > xBack) && (mouseY < yBack && mouseY > yBack - 65)) {
      if (Mouse.isButtonDown(0)) {
        stb.enterState(0, new FadeOutTransition(), new FadeInTransition());
      }
    } else if ((mouseX < xKey + 320 && mouseX > xKey) && (mouseY < yUp && mouseY > yUp - 84)) {
      if (input.isMousePressed(0)) {
        while (stallkey < 0) {
          drawUp("...");
          stallkey = Keyboard.getEventKey();
        }
        Player.setUpKey(stallkey);
        stallkey = -1;
      }
    } else if ((mouseX < xKey + 320 && mouseX > xKey)
        && (mouseY < yRight && mouseY > yRight - 84)) {
      if (input.isMousePressed(0)) {
        while (stallkey < 0) {
          drawRight("...");
          stallkey = Keyboard.getEventKey();
        }
        Player.setRightKey(stallkey);
        stallkey = -1;
      }
    } else if ((mouseX < xKey + 320 && mouseX > xKey) && (mouseY < yLeft && mouseY > yLeft - 84)) {
      if (input.isMousePressed(0)) {
        while (stallkey < 0) {
          drawLeft("...");
          stallkey = Keyboard.getEventKey();
        }
        Player.setLeftKey(stallkey);
        stallkey = -1;
      }
    } else if ((mouseX < xKey + 320 && mouseX > xKey) && (mouseY < yDown && mouseY > yDown - 84)) {
      if (input.isMousePressed(0)) {
        while (stallkey < 0) {
          drawDown("...");
          stallkey = Keyboard.getEventKey();
        }
        Player.setDownKey(stallkey);
        stallkey = -1;
      }
    }
  }
}
