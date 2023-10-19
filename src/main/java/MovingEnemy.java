import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

/** Moving enemy Class implements the Character interface
 *
 */
public class MovingEnemy implements Character {
    float x;
    float y;
    public String availableMoveS="udlr";
    public ArrayList<java.lang.Character> availableMovesConvert =new ArrayList<java.lang.Character>();
    TheGrid theGrid = new TheGrid();

    /**
     *
     * @param startX Set the initial X coordinate of a new instance of  MovingEnemy
     * @param startY Set the initial Y coordinate of a new instance of  MovingEnemy
     */
    public MovingEnemy(float startX, float startY)
    {
        this.x = startX;
        this.y = startY;
        for (char ch: availableMoveS.toCharArray())
        {
            availableMovesConvert.add(ch);
        }
    }

    /**
     *
     * @param dir this direction will decide how x and y change
     */
    public void move(char dir)
    {
        if(dir == 'r')
        {
            x = x + 32;
        }
        else if(dir == 'l')
        {
            x = x -32;
        }
        else if(dir == 'u')
        {
            y = y - 32;
        }
        else if(dir == 'd')
        {
            y = y + 32;
        }
        theGrid.setTrueTileAt(x, y,3);
    }

    /**
     *
     * @return returns current X position of the moving enemy
     */
    public float getXPosition()
    {
        return x;
    }

    /**
     *
     * @return returns current Y position of the moving enemy
     */
    public float getYPosition()
    {
        return y;
    }

    /**
     *
     * @param x set the X coordinate to x
     */
    public void setXPosition(float x)
    {
        this.x = x;
    }

    /**
     *
     * @param y set the Y coordinate to y
     */
    public void setYPosition(float y)
    {
        this.y = y;
    }

    /**
     *
     * @return returns a random move from the set of available movements
     */
    public char randomMove()
    {
        char result;
        Random rand= new Random();
        result=availableMovesConvert.get(rand.nextInt(availableMovesConvert.toArray().length));
        return  result;
    }

    /**
     *
     * @param move a character to remove from the set of available movements
     */
    public void removeAvailableMoves(char move)
    {
        boolean remove = this.availableMovesConvert.remove(new java.lang.Character(move));

    }

    /**
     * resets to the initial set of available movements
     */
    public void resetMoves()
    {
        availableMovesConvert.clear();
        for (char ch: this.availableMoveS.toCharArray())
        {
            this.availableMovesConvert.add(ch);
        }
    }

    /**
     *
     * @param x x coordinates of the player's location
     * @param y y coordinates of the player's location
     * @return returns the shortest distance to the player
     */
    public int heuristic(int x, int y)
    {
        int xEnemy=(int)(this.x/32);
        int yEnemy=(int) ((this.y-144)/32);
        return (abs(xEnemy-x)+abs(yEnemy-y));
    }
    /**
     * A method for updating the position of the moving enemy in every 4 ticks.
     */
    public char updateEnemy(int xPosPlayer, int yPosPlayer)
    {
        /**
         * Check whether an adjacent cell of the moving enemy is a wall
         * if that is the case, remove the enemy's available movements on that direction (initially available movements
         * are "uldr")
         */
        if ((TheGrid.getPixelType(this.getXPosition(), this.getYPosition() - 32)) == 1)
        {
            this.removeAvailableMoves('u');
        }
        if(TheGrid.getPixelType(this.getXPosition() - 32, this.getYPosition()) == 1)
        {
            this.removeAvailableMoves('l');
        }
        if (TheGrid.getPixelType(this.getXPosition(), this.getYPosition() + 32) == 1)
        {
            this.removeAvailableMoves('d');
        }
        if(TheGrid.getPixelType(this.getXPosition() + 32, this.getYPosition())== 1)
        {
            this.removeAvailableMoves('r');
        }
        /**
         * heuristicValues array keeps track of heuristic values of the enemy position
         * heuristic values are the shortest distance of the enemy to the player
         */
        char BestMove;
        ArrayList<Integer> heuristicValues
                = new ArrayList<Integer>(this.availableMovesConvert.size());
        for(int i=0;i<this.availableMovesConvert.size();i++)
        {
            /**
             * Check the enemy heuristic values if the enemy was on the available cell
             * and store the value inside the heuristicValue arraylist.
             */
            if (this.availableMovesConvert.get(i)=='u')
            {
                //getting coordinates of the player and enemy on the board
                MovingEnemy newState=new MovingEnemy(this.getXPosition(),this.getYPosition()-32);
                heuristicValues.add(newState.heuristic(xPosPlayer,yPosPlayer));

            }
            else if (this.availableMovesConvert.get(i)=='l')
            {

                MovingEnemy newState=new MovingEnemy(this.getXPosition()-32,this.getYPosition());
                heuristicValues.add(newState.heuristic(xPosPlayer,yPosPlayer));
            }
            else if (this.availableMovesConvert.get(i)=='d')
            {

                MovingEnemy newState=new MovingEnemy(this.getXPosition(),this.getYPosition()+32);
                heuristicValues.add(newState.heuristic(xPosPlayer,yPosPlayer));
            }
            else if (this.availableMovesConvert.get(i)=='r')
            {
                MovingEnemy newState=new MovingEnemy(this.getXPosition()+32,this.getYPosition());
                heuristicValues.add(newState.heuristic(xPosPlayer,yPosPlayer));
            }
        }
        /**
         * get the minimum value from the heuristicValues arraylist list and map to it's corresponding movement into
         * bestMove
         * the enemy moves according to the bestMove value
         */
        int min=heuristicValues.get(0);
        for (int j= 1; j < heuristicValues.size(); j++) {
            if (heuristicValues.get(j) < min) {
                min = heuristicValues.get(j);
            }
        }
        // set the movement based on the lowest heuristic value
        int index=heuristicValues.indexOf(min);
        BestMove=this.availableMovesConvert.get(index);
        this.move(BestMove);
        char dir = BestMove;
        this.resetMoves();
        return dir;
    }


}
