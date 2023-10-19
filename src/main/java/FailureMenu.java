import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;

import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

/**
 *  The class leads to the drawing and updating of failure menu if the player loses
 */
public class FailureMenu extends BasicGameState{
    int xPos= 900/2;
    int yPos= 1100/2;
    Image exit;
    Image background;
    Image restart;
    Input input;


    //Placeholder score and time
    long currentTime;
    int timeCatch;

    //Font for score and time
    Font font;

    private static TrueTypeFont gothic;

    Scoreboard sb=Scoreboard.getInstance();

    /**
     * Non default constructor for Failure menu.
     * @param state of the failure menu on the game.
     */
    public FailureMenu(int state){

    }

    /**
     * Gives the game state of the failure menu
     * @return the game state
     */
    @Override
    /**
     * returns the ID of the  Failure menu state
     */
    public int getID()
    {
        return 6;
    }

    /**
     * To get the background image and failure menu image including options like exit,restart and time
     * @param gc is the game container.
     * @param sbg is the state based game.
     * @throws SlickException
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //Placeholder background image
        background=new Image("src/main/resources/failureBG.png");
        //Exit button
        exit =new Image("src/main/resources/Exit.png");
        restart=new Image("src/main/resources/Restart Level.png");

        try {
            font=Font.createFont(Font.TRUETYPE_FONT,new File("src/main/resources/Gothic_Birthday_Cake.ttf")).deriveFont(24f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gothic=new TrueTypeFont(font,true);

    }

    @Override
    /**
     * Draws the image of the graphics including the background and options like exit,restart and exit to main.
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setFont(gothic);

        g.drawImage(background,0,0);
        exit.draw(xPos,yPos);
        restart.draw(xPos,yPos-84);

        g.drawString("Score :       "+sb.getScore(),xPos+20,400 -20);
        g.drawString("Time :        "+currentTime,xPos+20,400+20);

    }

    @Override
    /**
     * Updates the game and includes time catch which grabs the current time the game was paused.
     */
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        input=gc.getInput();

        if(timeCatch<1)
        {
            currentTime= sb.getTime();
            timeCatch++;
        }
        int mouseX= Mouse.getX();
        int mouseY=Mouse.getY();

        //Y values for exit and restart button
        int yExit=720-(yPos+84);
        int yRestart =720-(yPos);

        if( (mouseX<xPos+320 && mouseX >xPos) && (mouseY<yExit+84 && mouseY>yExit))
        {
            if (input.isMousePressed(0))
            {
               System.exit(0);
            }
        }

        if( (mouseX<xPos+320 && mouseX >xPos) && (mouseY< yRestart +84 && mouseY> yRestart))
        {
            if (input.isMousePressed(0))
            {
                GameScreen.isPlaying = false;
                restartGame(sbg);
            }
        }



    }

    /**
     * Restarts the game with the original set up.
     * @param sbg
     * @throws SlickException
     */
    public void restartGame(StateBasedGame sbg) throws SlickException {
        sbg.getState(1).init(sbg.getContainer(),sbg);
        sbg.enterState(1);
    }
}

