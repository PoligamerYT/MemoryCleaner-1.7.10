package xyz.tcreopargh.memorycleaner;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        if (Config.cleanOnInit) {
            MemoryManager.cleanMemory();
        }
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new F3Handler());
        super.init(event);
        if (Config.cleanOnInit) {
            MemoryManager.cleanMemory();
        }
    }

    public void postInit(FMLPostInitializationEvent event) {
        MemoryCleaner.logger.info("Registering Command: " + CleanMemoryCommand.NAME);
        ClientCommandHandler.instance.registerCommand(new CleanMemoryCommand());
        super.postInit(event);
        if (Config.cleanOnInit) {
            MemoryManager.cleanMemory();
        }
    }
}
