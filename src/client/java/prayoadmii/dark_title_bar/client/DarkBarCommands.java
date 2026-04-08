package prayoadmii.dark_title_bar.client;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class DarkBarCommands {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal("darkbar")
                .then(literal("dark").executes(ctx -> {
                    TitleBarController.setDark(true);

                    DarkTitleBarForMinecraftClient.config.darkBarEnabled = true;
                    DarkTitleBarForMinecraftClient.config.save();

                    return 1;
                }))
                .then(literal("lignt").executes(ctx -> {
                    TitleBarController.setDark(false);

                    DarkTitleBarForMinecraftClient.config.darkBarEnabled = false;
                    DarkTitleBarForMinecraftClient.config.save();

                    return 1;
                }))
            );
        });
    }
}