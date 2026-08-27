package git.noobs_create.darkbar.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import git.noobs_create.darkbar.client.controller.DarkBarCommands;
import git.noobs_create.darkbar.client.controller.TitleBarController;
import git.noobs_create.darkbar.client.helper.Config;

public class DarkBarMain implements ClientModInitializer {
    public static Config config;

    public static final Logger LOGGER = LoggerFactory.getLogger("DarkBar");

    @Override
    public void onInitializeClient() {
        config = Config.load();

        ClientTickEvents.END_CLIENT_TICK.register(new OneTimeDarkBar());

        String os = System.getProperty("os.name", "").toLowerCase();

        if (!os.contains("win")) {
            LOGGER.warn("DarkBar: Mod Disabled! Make Sure You're On Windows!");

            return;
        }

        LOGGER.info("Client Initialized Successfully!");

        DarkBarCommands.register();
    }

    private static class OneTimeDarkBar implements ClientTickEvents.EndTick {
        private boolean applied = false;

        @Override
        public void onEndTick(net.minecraft.client.Minecraft client) {
            if (!applied && client.getWindow() != null && client.getWindow().handle() != 0) {
                TitleBarController.setDark(DarkBarMain.config.darkBarEnabled);

                applied = true;
            }
        }
    }
}