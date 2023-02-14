
package javakun;

import net.minecraftforge.common.config.*;

public class ConfigHandler
{
    private Configuration configs;
    
    public ConfigHandler(final Configuration configs) {
        this.configs = configs;
    }
    
    public void loadConfig() {
        this.configs.load();
        Property prop = this.configs.get("general", "delay", 180.0);
        prop.comment = "Delay to clear the memory in seconds (set to 0 to turn off the mod)";
        MemoryCleaner.delay = (long)prop.getDouble((double)MemoryCleaner.delay);
        prop = this.configs.get("general", "debug", false);
        prop.comment = "Output in console the memory when clear";
        MemoryCleaner.consoleDebug = prop.getBoolean(MemoryCleaner.consoleDebug);
        if (this.configs.hasChanged()) {
            this.configs.save();
        }
        if (MemoryCleaner.delay == 0L) {
            MemoryCleaner.modOn = false;
        }
    }
    
    public void syncConfigs() {
        Property prop = this.configs.get("general", "delay", 180.0);
        prop.comment = "Delay to clear the memory in seconds (set to 0 to turn off the mod)";
        prop.set((double)MemoryCleaner.delay);
        prop = this.configs.get("general", "debug", false);
        prop.comment = "Output in console the memory when clear";
        prop.set(MemoryCleaner.consoleDebug);
        if (this.configs.hasChanged()) {
            this.configs.save();
        }
    }
}
