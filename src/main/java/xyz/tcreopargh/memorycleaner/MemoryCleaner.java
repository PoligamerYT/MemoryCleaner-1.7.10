package xyz.tcreopargh.memorycleaner;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;
import java.util.Map;

@Mod(modid = MemoryCleaner.MOD_ID, version = Tags.VERSION, name = "Memory Cleaner", acceptedMinecraftVersions = "[1.7.10]")
public class MemoryCleaner {

    public static final String MOD_ID = "memorycleaner";
    public static final String CLIENT_PROXY = "xyz.tcreopargh.memorycleaner.ClientProxy";
    public static final String COMMON_PROXY = "xyz.tcreopargh.memorycleaner.CommonProxy";
    @Mod.Instance(MOD_ID)
    public static MemoryCleaner INSTANCE;
    public static Logger logger;
    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @NetworkCheckHandler
    public boolean checkModList(final Map<String, String> versions, final Side side) {
        return true;
    }

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}