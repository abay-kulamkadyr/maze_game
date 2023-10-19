import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntegrationMovingPlayerTest {
    Player player;
    MovingEnemy enemy;
    boolean Check;
    TheGrid theGrid = new TheGrid(300,300,144,32);

    @Before
    public void setup()
    {
        player=new Player(32*2,32*2+144);

        theGrid=new TheGrid();
        theGrid.setTileAt(player.getGridX(),player.getGridY(),2);
        //surround the tiles on the diagonal with walls
        theGrid.setTileAt(1,1,1);
        theGrid.setTileAt(1,3,1);
        theGrid.setTileAt(3,1,1);
        theGrid.setTileAt(3,3,1);
    }
    @Test
    public void CheckRight()
    {
        Check=false;
        enemy=new MovingEnemy(32*2,32*3+144);
        TheGrid.setTrueTileAt(enemy.getXPosition(),enemy.getYPosition(),3);
        enemy.updateEnemy(player.getGridX(), player.getGridY());

        if(enemy.getXPosition()== player.getXPosition()&& enemy.getYPosition()==player.getYPosition())
        {
            Check=true;
        }
        assert (Check);
    }
    @Test
    public void CheckLeft(){
        Check=false;
        enemy=new MovingEnemy(32*2,32+144);
        TheGrid.setTrueTileAt(enemy.getXPosition(),enemy.getYPosition(),3);
        enemy.updateEnemy(player.getGridX(), player.getGridY());

        if(enemy.getXPosition()== player.getXPosition()&& enemy.getYPosition()==player.getYPosition())
        {
            Check=true;
        }
        assert (Check);
    }
    @Test
    public void CheckUp(){
        Check=false;
        enemy=new MovingEnemy(32,32*2+144);
        TheGrid.setTrueTileAt(enemy.getXPosition(),enemy.getYPosition(),3);
        enemy.updateEnemy(player.getGridX(), player.getGridY());

        if(enemy.getXPosition()== player.getXPosition()&& enemy.getYPosition()==player.getYPosition())
        {
            Check=true;
        }
        assert (Check);

    }
    @Test
    public void CheckDown()
    {
        Check=false;
        enemy=new MovingEnemy(32*3,32*2+144);
        TheGrid.setTrueTileAt(enemy.getXPosition(),enemy.getYPosition(),3);
        enemy.updateEnemy(player.getGridX(), player.getGridY());

        if(enemy.getXPosition()== player.getXPosition()&& enemy.getYPosition()==player.getYPosition())
        {
            Check=true;
        }
        assert (Check);
    }


}
