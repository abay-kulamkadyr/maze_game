

/**
 * The Scoreboard keeps track of the timing for menus and the overall score of the user.
 */
public class Scoreboard {
    private static Scoreboard instance=null;
    private int score;
    private long timer;
    private long startTime;
    private long stoppedTime;
    private long startPause;

    /**
     * Creates a new Scoreboard with time and score as 0. Along with the startTime being the current time.
     */
    public Scoreboard(){
        this.score=0;
        this.timer=0;
        this.startTime=System.currentTimeMillis();
        this.stoppedTime=0;
        this.startPause=0;
    }

    //Uses singleton to create a scoreboard object that is accessible by other classes
    //Returns instance of object to caller

    /**
     * Uses singleton to create a scoreboard that other classes can access.
     * @return the current instance of the object to the caller.
     */
    public static Scoreboard getInstance(){
        if (instance==null){
            instance=new Scoreboard();
        }
        return instance;
    }

    /**
     * Gets the current time in seconds that the scoreboard is at.
     * @return the current time the scoreboard has.
     */
    public long getTime(){
        long now=System.currentTimeMillis();
        timer=(now-startTime-stoppedTime)/1000;
        return timer;
    }

    /**
     * Stops the timer from continuing. Gets the time in which it called pause.
     */
    public void pause(){
        startPause=System.currentTimeMillis();
    }

    /**
     * Resume the timer by getting the time it was stopped for and calculating it's current time.
     */
    public void resume(){
        stoppedTime+=System.currentTimeMillis()-startPause;
        startPause=0;
    }

    /**
     *
     * @return the current score.
     */
    public int getScore(){
        return score;
    }

    /**
     * Adds to the score by the parameter n.
     * @param n amount to be added to score.
     */
    public void setScore(int n){
        score+=n;
    }

    /**
     * Resets the startTime to whenever the current time is. This is used so the displayed timer resets when leaving
     * the main menu.
     */
    public void secretResetMethod(){
        this.startTime=System.currentTimeMillis();
        this.stoppedTime=0;
        this.score=0;
    }
}
