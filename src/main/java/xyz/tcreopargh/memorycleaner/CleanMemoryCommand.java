package xyz.tcreopargh.memorycleaner;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class CleanMemoryCommand extends CommandBase {

    public static final String NAME = "cleanmemory";

    @Override
    public String getCommandName() {
        return NAME;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/cleanmemory";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<>();
        for (String string : Config.commandAliases.split("\\s+")) {
            if (!string.isEmpty()) {
                aliases.add(string);
            }
        }
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        MemoryManager.cleanMemory(sender);
        Events.lastCleanTime = System.currentTimeMillis();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
