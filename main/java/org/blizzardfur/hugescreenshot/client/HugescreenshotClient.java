package org.blizzardfur.hugescreenshot.client;

import com.mojang.blaze3d.platform.GlDebugInfo;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.io.File;
import java.nio.ByteBuffer;

public class HugescreenshotClient implements ClientModInitializer {
    private static KeyBinding keyBinding,keyBindingtwo;
    public GameRenderer gameRenderer;
    public File mcDataDir;



    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Take Huge .tga Screenshot", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_F7, // The keycode of the key
                "Huge Screenshots and Panoramas" // The translation key of the keybinding's category.
        ));

        keyBindingtwo = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Take Panorama", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_F8, // The keycode of the key
                "Huge Screenshots and Panoramas" // The translation key of the keybinding's category.
        ));


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                client.takeHugeScreenshot(mcDataDir, client.getWindow().getWidth(), client.getWindow().getHeight(), 36450, 17700);
            }
            while (keyBindingtwo.wasPressed()) {
                client.takePanorama(mcDataDir, client.getWindow().getWidth(), client.getWindow().getHeight());
                System.out.println("test");
            }
        });
    }
}