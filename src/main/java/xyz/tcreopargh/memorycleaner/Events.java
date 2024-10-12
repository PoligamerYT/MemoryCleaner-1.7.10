package xyz.tcreopargh.memorycleaner;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static xyz.tcreopargh.memorycleaner.MemoryManager.cleanMemory;

public final class Events {

    public static long lastCleanTime;

    public static List<UUID> recognizedPlayers = new ArrayList<>();

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (!Minecraft.getMinecraft().isGamePaused() && event.phase == TickEvent.Phase.END && player != null && player.worldObj.isRemote) {
            boolean doClean = false;
            if ((System.currentTimeMillis() - lastCleanTime) > Config.AutomaticCleanup.interval * 1000L) {
                if ((double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / Runtime.getRuntime().totalMemory() > Config.forceCleanPercentage / 100.0D) {
                    doClean = true;
                } else if (Config.AutomaticCleanup.autoCleanup) {
                    doClean = true;
                }

                if (doClean) {
                    cleanMemory(player);
                    lastCleanTime = System.currentTimeMillis();
                }
            }
        }
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPlayerLogin(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer && event.world.isRemote) {
            EntityPlayer player = (EntityPlayer) event.entity;
            if (player.getUniqueID().equals(Minecraft.getMinecraft().thePlayer.getUniqueID())) {
                if (!recognizedPlayers.contains(player.getUniqueID())) {
                    if (Config.cleanOnJoin) {
                        cleanMemory(player);
                    }
                    lastCleanTime = System.currentTimeMillis();
                    recognizedPlayers.add(player.getUniqueID());
                }
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.modID.equals(MemoryCleaner.MOD_ID)) {
            MemoryCleaner.logger.info("MemoryCleaner Config Changed!");
            Config.loadConfig(Config.configFile);
        }
    }
}
