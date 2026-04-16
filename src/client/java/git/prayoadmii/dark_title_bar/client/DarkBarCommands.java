package git.prayoadmii.dark_title_bar.client;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

public class DarkBarCommands {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                LiteralArgumentBuilder.<FabricClientCommandSource>literal("darkbar")
                
                .then(LiteralArgumentBuilder.<FabricClientCommandSource>literal("dark")
                    .executes(ctx -> {
                        TitleBarController.setDark(true);

                        DarkTitleBarForMinecraftClient.config.darkBarEnabled = true;
                        DarkTitleBarForMinecraftClient.config.save();

                        return 1;
                    })
                )

                .then(LiteralArgumentBuilder.<FabricClientCommandSource>literal("light")
                    .executes(ctx -> {
                        TitleBarController.setDark(false);

                        DarkTitleBarForMinecraftClient.config.darkBarEnabled = false;
                        DarkTitleBarForMinecraftClient.config.save();

                        return 1;
                    })
                )
            );
        });
    }
}