package com.headhud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.PlayerSkinDrawer;
import net.minecraft.client.render.RenderTickCounter;

public class HeadHudClient implements ClientModInitializer {

    // Head ka size chota kar diya (16 se 12)
    private static final int SIZE = 14;
    // Perfect center ke liye OFFSET_X ko 0 rakha hai
    private static final int OFFSET_X = 0;  
    // Head ko aur upar karne ke liye OFFSET_Y ko -16 kar diya
    private static final int OFFSET_Y = -13; 

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(HeadHudClient::renderHead);
    }

    private static void renderHead(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.options.hudHidden) return;

        int screenW = context.getScaledWindowWidth();
        int screenH = context.getScaledWindowHeight();

        // Hearts aur Hunger bar ke bilkul center me set karne ke liye
        int x = (screenW / 2) - (SIZE / 2) + OFFSET_X;
        int y = screenH - 39 + OFFSET_Y;

        PlayerSkinDrawer.draw(context, mc.player.getSkinTextures(), x, y, SIZE);
    }
}
