
package javakun;

import org.apache.logging.log4j.*;
import net.minecraftforge.common.config.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import net.minecraft.command.*;

@Mod(modid = "MemoryCleaner", version = "1.0", name = "Memory Cleaner", canBeDeactivated = true)
public class MemoryCleaner
{
    public static final String MODID = "MemoryCleaner";
    public static final String NAME = "Memory Cleaner";
    public static final String VERSION = "1.0";
    @Mod.Instance("MemoryCleaner")
    public static MemoryCleaner instance;
    public static ConfigHandler configs;
    public static long delay;
    public static boolean consoleDebug;
    public static boolean modOn;
    public static Logger log;
    public static boolean running;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        MemoryCleaner.instance = new MemoryCleaner();
        MemoryCleaner.configs = new ConfigHandler(new Configuration(event.getSuggestedConfigurationFile()));
        MemoryCleaner.log = event.getModLog();
        MemoryCleaner.configs.loadConfig();
        FMLCommonHandler.instance().bus().register((Object)MemoryCleaner.instance);
    }
    
    @Mod.EventHandler
    public void postInitEvent(final FMLPostInitializationEvent event) {
        Cleaner.play();
    }
    
    @Mod.EventHandler
    public void onServerStartedEvent(final FMLServerStartedEvent event) {
        MemoryCleaner.running = true;
    }
    
    @Mod.EventHandler
    public void onServerStoppedEvent(final FMLServerStoppedEvent event) {
        MemoryCleaner.running = false;
    }
    
    @Mod.EventHandler
    public void onServerStartingEvent(final FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new Commands());
    }
    
    static {
        MemoryCleaner.delay = 180L;
        MemoryCleaner.consoleDebug = false;
        MemoryCleaner.modOn = true;
        MemoryCleaner.running = false;
    }
}
