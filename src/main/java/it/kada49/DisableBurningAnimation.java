package it.kada49;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;


@Mod(name = "DisableBurningAnimation", modid = DisableBurningAnimation.ID, version = "1.0", updateJSON = "https://kada49.github.io/json/DisableBurningAnimation-updateJson.json")
public class DisableBurningAnimation {

    public static final String ID = "disableburninganimation";

    public static Property ENABLED;
    public static Configuration CONFIG;

    @EventHandler
    @SuppressWarnings("unused")
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new ToggleCommand());

        MinecraftForge.EVENT_BUS.register(this);

        File configFile = new File(Loader.instance().getConfigDir(), ID + ".cfg");
        CONFIG = new Configuration(configFile);
        CONFIG.load();
        ENABLED = CONFIG.get("General", "burningEnabled", true);
        CONFIG.save();
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void blockOverlayEvent(RenderBlockOverlayEvent event) {
        if (event.overlayType == RenderBlockOverlayEvent.OverlayType.FIRE && !ENABLED.getBoolean()) {
            event.setCanceled(true);
        }
    }
}