package com.trollmaster;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.command.CommandManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.text.Text;

public class TrollmasterMod implements ModInitializer {
    public static final String MOD_ID = "trollmaster107";

    @Override
    public.onInitialize() {
        System.out.println("Trollmaster 107 initialized with 106 traps!");

        // 1. Tính năng mạo danh chat (Chat Spoofing)
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("spoofchat")
                .then(CommandManager.argument("victim", StringArgumentType.word())
                .then(CommandManager.argument("message", StringArgumentType.greedyString())
                .executes(context -> {
                    String victim = StringArgumentType.getString(context, "victim");
                    String message = StringArgumentType.getString(context, "message");
                    
                    // Gửi tin nhắn giả mạo hiện lên khung chat của mọi người như thể nạn nhân tự nói
                    Text fakeText = Text.literal("<" + victim + "> " + message);
                    context.getSource().getServer().getPlayerManager().broadcast(fakeText, false);
                    return 1;
                }))));
        });
    }
}