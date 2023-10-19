import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Creates the main menu of the game with options like exit,settings,lore and start.
 */
public class MainMenu extends BasicGameState {
    Image start;
    Image lore;
    Image settings;
    Image exit;
    int xPos= 900/2;
    int yPos= 1100/2;



    Scoreboard sb=Scoreboard.getInstance();

    /**
     * To know the state of the Main Menu.
     * @param state of the Main menu on the game.
     */
    public MainMenu(int state)
    {

    }

    /**
     * Initializes all the resources for the game state like exit,settings,lore and start.
     * @param gc Game container of the game
     * @param sbg state based game
     * @throws SlickException
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        exit =new Image("src/main/resources/Exit.png");
        settings=new Image("src/main/resources/Settings.png");
        lore=new Image("src/main/resources/Lore.png");
        start=new Image("src/main/resources/Start Game.png");

    }

    @Override
    /**
     * Draws everything on the Game Screen using the render method
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {


        Image background=new Image("src/main/resources/Menu.png");

        g.drawImage(background,0,0);
        exit.draw(xPos,yPos);
        settings.draw(xPos,yPos-84);
       lore.draw(xPos,yPos-(84*2));
        start.draw(xPos,yPos-84*3);

    }

    @Override
    /**
     * Updating initialized variables and objects.
     */

    public void update(GameContainer gc, StateBasedGame stb, int delta) throws SlickException {
        //getting coordinates of mouse
        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();
        //update coordinates of the buttons since mouse input coordinates start from bottom left corner
        int yExit = 720 - (yPos);
        int ySettings = 720 - (yPos - 84);
        int yLore = 720 - (yPos - (84 * 2));
        int yStart = 720 - (yPos - 84 * 3);
        if ((mouseX < xPos + 320 && mouseX > xPos) && (mouseY < yExit && mouseY > yExit - 84)) {
            if (Mouse.isButtonDown(0)) {
                System.exit(0);
            }
        } else if ((mouseX < xPos + 320 && mouseX > xPos) && (mouseY < yStart && mouseY > yStart - 84)) {
            if (Mouse.isButtonDown(0)) {

                stb.getState(1).init(stb.getContainer(), stb);
                sb.secretResetMethod();
                GameScreen.isPlaying = false;
                stb.enterState(1, new FadeOutTransition(), new FadeInTransition());
            }
        } else if ((mouseX < xPos + 320 && mouseX > xPos) && (mouseY < yLore && mouseY > yLore - 84)){
            if (Mouse.isButtonDown(0)) {
                stb.enterState(2, new FadeOutTransition(), new FadeInTransition());
                System.out.println("Clicked Lore");
            }
    }
            else if ((mouseX<xPos+320 && mouseX>xPos)&& (mouseY<ySettings && mouseY>ySettings-84)){
                if(Mouse.isButtonDown(0)){
                    System.out.println("Settings");
                    stb.enterState(3,new FadeOutTransition(),new FadeInTransition());

                }
        }

    }

    @Override
    /**
     * Returns the ID of the MainMenu state
     */
    public int getID()
    {
        return 0;
    }
}
