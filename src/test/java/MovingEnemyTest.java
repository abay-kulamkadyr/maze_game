import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovingEnemyTest {

    MovingEnemy skully;
    TheGrid theGrid = new TheGrid(300,300,0,32);

    @Before
    public void setUp()
    {
        skully=new MovingEnemy(123,254);
        skully.setXPosition(123);
        skully.setYPosition(254);
    }
    @Test
    public void MoveLeft(){
        skully.move('l');
        int save=(int)skully.getXPosition();
        assertEquals(123-32,save);
    }
    @Test
    public void MoveRight()
    {
        skully.move('r');
        int save=(int)skully.getXPosition();
        assertEquals(123+32,save);
    }
    @Test
    public void MoveUp()
    {
        skully.move('u');
        int save=(int)skully.getYPosition();
        assertEquals(254-32,save);
    }
    @Test
    public void MoveDown() {
        skully.move('d');
        int save=(int)skully.getYPosition();
        assertEquals(254+32,save);
    }

    @Test
    public void randomMove() {
        String hold= String.valueOf(skully.randomMove());
        assert(skully.availableMoveS.contains(hold));
    }

    @Test
    public void removeAvailableMoves() {
        char hold=skully.randomMove();
        skully.removeAvailableMoves(hold);
        assert(!skully.availableMovesConvert.contains(hold));
    }

    @Test
    public void resetMoves() {
        char hold=skully.randomMove();
        skully.removeAvailableMoves(hold);
        skully.resetMoves();
        StringBuilder builder=new StringBuilder(skully.availableMovesConvert.size());
        for(java.lang.Character ch:skully.availableMovesConvert)
        {
            builder.append(ch);
        }
        assertEquals(skully.availableMoveS,builder.toString());
    }

    @Test
    public void heuristic() {
        //setting position of the player at(5,3)
        int xPosPlayer=5;
        int yPosPlayer=3;
        // setting enemy at position (15,7)
        skully= new MovingEnemy(15*32,7*32+144);
        //shortest distance to the player should be 14 steps
        int hold= skully.heuristic(xPosPlayer,yPosPlayer);
        assertEquals(14, hold);
    }
}