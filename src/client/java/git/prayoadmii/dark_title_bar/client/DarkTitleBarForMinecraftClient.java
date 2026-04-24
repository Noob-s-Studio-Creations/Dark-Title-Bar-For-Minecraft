package git.prayoadmii.dark_title_bar.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DarkTitleBarForMinecraftClient implements ClientModInitializer {
    public static Config config;

    public static final Logger LOGGER = LoggerFactory.getLogger("DarkBar");

    @Override
    public void onInitializeClient() {
        config = Config.load();

        ClientTickEvents.END_CLIENT_TICK.register(new OneTimeDarkBar());

        LOGGER.info("Client Initialized Successfully!");

        DarkBarCommands.register();
    }

    private static class OneTimeDarkBar implements ClientTickEvents.EndTick {
        private boolean applied = false;

        @Override
        public void onEndTick(net.minecraft.client.Minecraft client) {
            if (!applied && client.getWindow() != null && client.getWindow().handle() != 0) {
                TitleBarController.setDark(DarkTitleBarForMinecraftClient.config.darkBarEnabled);

                applied = true;
            }
        }
    }
}