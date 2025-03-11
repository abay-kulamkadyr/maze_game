import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.input.Keyboard;

public class PlayerTest {
  Player jim;

  @Before
  public void setup() {
    jim = new Player(192, 336);
  }

  @Test
  public void moveLeft() {
    jim.move('l');
    int val = (int) jim.getXPosition();
    assertEquals(160, val);
  }

  @Test
  public void moveRight() {
    jim.move('r');
    int val = (int) jim.getXPosition();
    assertEquals(224, val);
  }

  @Test
  public void moveUp() {
    jim.move('u');
    int val = (int) jim.getYPosition();
    assertEquals(304, val);
  }

  @Test
  public void moveDown() {
    jim.move('d');
    int val = (int) jim.getYPosition();
    assertEquals(368, val);
  }

  @Test
  public void testX() {
    jim.setXPosition(128);
    int val = (int) jim.getXPosition();
    assertEquals(128, val);
  }

  @Test
  public void testY() {
    jim.setYPosition(256);
    int val = (int) jim.getYPosition();
    assertEquals(256, val);
  }

  @Test
  public void testGridX() {
    assertEquals(6, jim.getGridX());
  }

  @Test
  public void testGridY() {
    assertEquals(6, jim.getGridY());
  }

  @Test
  public void testUpKey() {
    Player.setUpKey(Keyboard.KEY_E);
    assertEquals(Keyboard.KEY_E, Player.up);
  }

  @Test
  public void testDownKey() {
    Player.setDownKey(Keyboard.KEY_D);
    assertEquals(Keyboard.KEY_D, Player.down);
  }

  @Test
  public void testLeftKey() {
    Player.setLeftKey(Keyboard.KEY_S);
    assertEquals(Keyboard.KEY_S, Player.left);
  }

  @Test
  public void testRightKey() {
    Player.setRightKey(Keyboard.KEY_F);
    assertEquals(Keyboard.KEY_F, Player.right);
  }
}
