package it.kada49;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

import static it.kada49.DisableBurningAnimation.CONFIG;
import static it.kada49.DisableBurningAnimation.ENABLED;

public class ToggleCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return DisableBurningAnimation.ID;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        ENABLED.set(!ENABLED.getBoolean());
        CONFIG.save();

        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Burning Animation " + (ENABLED.getBoolean() ? "enabled" : "disabled")));
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("dba");
        return aliases;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
