
package javakun;

import org.apache.logging.log4j.*;

public class Cleaner extends Thread
{
    public static Cleaner instance;
    static Logger log;
    static Runtime runtime;
    
    public Cleaner() {
        super(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (MemoryCleaner.modOn) {
                        Cleaner.clear();
                    }
                    try {
                        Thread.sleep(MemoryCleaner.delay * 1000L);
                    }
                    catch (InterruptedException e) {}
                }
            }
        });
    }
    
    public static void play() {
        (Cleaner.instance = new Cleaner()).start();
    }
    
    public static void clear() {
        if (MemoryCleaner.running) {
            if (MemoryCleaner.consoleDebug) {
                Cleaner.log.info("_________________________________");
                Cleaner.log.info("Free: " + getFreeMemory() + "mb of " + getMaxMemory() + "mb| Used: " + getUsedMemory() + "mb");
                Cleaner.runtime.gc();
                Cleaner.log.info("Cleaned...");
                Cleaner.log.info("Free: " + getFreeMemory() + "mb of " + getMaxMemory() + "mb| Used: " + getUsedMemory() + "mb");
            }
            else {
                Cleaner.runtime.gc();
            }
        }
    }
    
    public static long getFreeMemory() {
        return Cleaner.runtime.freeMemory() / 1048576L;
    }
    
    public static long getMaxMemory() {
        return Cleaner.runtime.maxMemory() / 1048576L;
    }
    
    public static long getUsedMemory() {
        return (Cleaner.runtime.maxMemory() - Cleaner.runtime.freeMemory()) / 1048576L;
    }
    
    static {
        Cleaner.log = MemoryCleaner.log;
        Cleaner.runtime = Runtime.getRuntime();
    }
}
