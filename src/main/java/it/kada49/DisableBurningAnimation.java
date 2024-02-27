package it.kada49;

import com.mojang.brigadier.Command;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class DisableBurningAnimation implements ModInitializer {

	private static final String ID = "disableburninganimation";
	public static final Logger LOGGER = LogManager.getLogger(ID);
    private final File configFile = new File(FabricLoader.getInstance().getConfigDir().toAbsolutePath() + "/" + ID + ".json");

    public static boolean BURNING_ENABLED = true;

	@Override
	public void onInitialize() {

        if (!configFile.exists())
            Configuration.create(configFile);
        else {
            Configuration config = Configuration.get(configFile);
            BURNING_ENABLED = config.isBurningEnabled();
        }

        registerToggleCommand();
	}

    private void registerToggleCommand() {
        Command<FabricClientCommandSource> command = context -> {
            BURNING_ENABLED = !BURNING_ENABLED;
            Configuration.update(configFile);
            String message = "Burning animation " + (BURNING_ENABLED ? "enabled" : "disabled" ) + ".";
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal(message));
            return 1;
        };

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess)
                -> dispatcher.register(literal(ID).executes(command)));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess)
                -> dispatcher.register(literal("dba").executes(command)));
    }
}