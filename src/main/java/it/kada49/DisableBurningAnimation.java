package it.kada49;

import com.mojang.brigadier.Command;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class DisableBurningAnimation implements ModInitializer {

	public static final String ID = "disableburninganimation";

	public static boolean BURNING_ENABLED = true;
	public static Logger LOGGER = LogManager.getLogger(ID);


	@Override
	public void onInitialize() {
		Path configPath = FabricLoader.getInstance().getConfigDir();
		File configFile = new File(configPath.toFile().getAbsolutePath() + "/" + ID + ".json");

        if (!configFile.exists())
            Configuration.create(configFile);
        else {
            Configuration c = Configuration.get(configFile);
            BURNING_ENABLED = c.isBurningEnabled();
        }


        Command<FabricClientCommandSource> command = context -> {
            BURNING_ENABLED = !BURNING_ENABLED;
            Configuration.update(configFile, BURNING_ENABLED);
            return 1;
        };

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess)
				-> dispatcher.register(literal(ID).executes(command)));

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess)
				-> dispatcher.register(literal("dba").executes(command)));
	}
}