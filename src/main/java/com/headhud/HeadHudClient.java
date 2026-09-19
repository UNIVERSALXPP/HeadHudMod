package com.headhud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.PlayerSkinDrawer;
import net.minecraft.client.render.RenderTickCounter;

public class HeadHudClient implements ClientModInitializer {

    // Yaha se head ka size aur position badal sakte ho
    private static final int SIZE = 16;
    private static final int OFFSET_X = -4; // negative = aur left
    private static final int OFFSET_Y = -4; // negative = aur upar

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(HeadHudClient::renderHead);
    }

    private static void renderHead(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.options.hudHidden) return;

        int screenW = context.getScaledWindowWidth();
        int screenH = context.getScaledWindowHeight();

        // Hotbar ke left-top corner ke paas (health bar ke left me)
        int x = screenW / 2 - 91 - SIZE + OFFSET_X;
        int y = screenH - 39 + OFFSET_Y;

        PlayerSkinDrawer.draw(context, mc.player.getSkinTextures(), x, y, SIZE);
    }
}
