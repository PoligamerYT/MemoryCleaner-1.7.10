package xyz.tcreopargh.memorycleaner;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import java.io.File;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        MemoryCleaner.logger = event.getModLog();

        File configFile = new File(event.getModConfigurationDirectory(), "memorycleaner.cfg");

        Config.loadConfig(configFile);

        FMLCommonHandler.instance().bus().register(new Events());
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

    public void serverStarting(FMLServerStartingEvent event) {

    }
}
