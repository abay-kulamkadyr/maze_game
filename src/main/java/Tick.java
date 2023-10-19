import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Tick class follows the singleton design pattern.
 * To access it's only instance call Tick.instance() method
 * Has two private fields(tick, tickRunning) to count the number of tick has passed since the initial call of the class.
 * The class modifies the tick count with every Tick.instance() call.
 */
public class Tick
{
    private static int tick;
    private static int tickRunning;
    private static Tick theTick=null;

    /**
     * Private constructor, invoked only once
     * Set tick and tickRunning to 0
     */
    private Tick ()
    {
        tick=0;
        tickRunning=0;
    }

    /**
     * resets the tick variable to 0
     */
    static void resetTick()
    {
        tick=0;
    }

     /**
      *
      * @return returns the only instance of this class, i.ee theTick instance
     */
    static synchronized  Tick instance()
    {
        if (theTick==null)
        {
            theTick= new Tick();
        }

        return theTick;
    }

    /**
     *
     */
    static void increaseTick()
    {
        tick++; tickRunning++;
    }
    /**
     * @return returns the current value of tick
     */
    static int getTick()
    {

        return tick;
    }

    /**
     *
     * @return returns the current value of tickRunning
     */
    static int getTickRunning()
    {
        return tickRunning;
    }
}