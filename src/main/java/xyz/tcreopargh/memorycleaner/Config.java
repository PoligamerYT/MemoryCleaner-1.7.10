package xyz.tcreopargh.memorycleaner;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class Config {

    public static String commandAliases = "";
    public static boolean showMessage = true;
    public static boolean cleanOnJoin = true;
    public static boolean cleanOnInit = true;
    public static int forceCleanPercentage = 80;
    public static AutoCleanup AutomaticCleanup = new AutoCleanup();

    private static Configuration config;

    public static File configFile = null;

    public static class AutoCleanup {
        public boolean autoCleanup = true;
        public int interval = 30;
    }

    public static void loadConfig(File configFile_) {
        configFile = configFile_;

        config = new Configuration(configFile);

        try {
            config.load();

            commandAliases = config.getString("commandAliases", "General",
                    "", "Aliases for the memory cleaning command, separated by spaces.");

            showMessage = config.getBoolean("showMessage", "General",
                    true, "Whether to show a message when the cleaning starts or ends.");

            cleanOnJoin = config.getBoolean("cleanOnJoin", "General",
                    true, "Whether to clean memory immediately when joining a world.");

            cleanOnInit = config.getBoolean("cleanOnInit", "General",
                    true, "Whether to clean memory at important stages when Minecraft is initializing.");

            forceCleanPercentage = config.getInt("forceCleanPercentage", "General",
                    80, 0, 100, "If RAM usage exceeds this percentage, memory cleaning will start.");

            AutomaticCleanup.autoCleanup = config.getBoolean("autoCleanup", "AutomaticCleanup",
                    true, "Enable automatic time-based cleanup.");
            AutomaticCleanup.interval = config.getInt("interval", "AutomaticCleanup",
                    300, 30, Integer.MAX_VALUE, "The time, in seconds, between memory cleanings.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
