package xyz.tcreopargh.memorycleaner;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class F3Handler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderGameOverlayTextEvent(RenderGameOverlayEvent.Text  event) {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo && event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            event.left.add("");
            event.left.add(new ChatComponentTranslation("memorycleaner.f3.check").getFormattedText() + " " + Math.max(0, ((Config.AutomaticCleanup.interval * 1000L) - (System.currentTimeMillis() - Events.lastCleanTime)) / 1000L));
        }
    }
}
