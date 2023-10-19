import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import static java.lang.System.nanoTime;

/**
        * This class creates a thread
 * with a name "TickThread" and
         * calls singleton Tick class every 1/32 seconds
        * and increases tick counter
        */
class TickThread extends Thread
{

    TickThread()
    {
        super("TickThread");
    }

    /**
            * starts the Tick thread
     * if the this thread is running then run a while loop
     * until 1/32 of a second has passed
     * if 1/32 of a second has passed increase the tick count
     * via calling Tick.instance().increaseTick
     */
    public void run()
    {
        double unprocessedSeconds=0;
        long previousTime= nanoTime();

        double secondsForEachTick=1/32.0; // set to update tick every 2 seconds
        while(Thread.currentThread().isAlive())
        {
            //long start=System.currentTimeMillis();
            long currentTime= nanoTime();

            long passedTime=currentTime-previousTime;
            previousTime=currentTime;
            unprocessedSeconds = unprocessedSeconds + passedTime / 1000000000.0;
            while(unprocessedSeconds>secondsForEachTick)
            {
                unprocessedSeconds-=secondsForEachTick;
                Tick.instance().increaseTick();
            }
        }
    }
}