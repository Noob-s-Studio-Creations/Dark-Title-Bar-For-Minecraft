package prayoadmii.dark_title_bar.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

import org.lwjgl.glfw.GLFWNativeWin32;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

public class DarkTitleBarForMinecraftClient implements ClientModInitializer {
    interface DwmApi extends Library {
        DwmApi INSTANCE = Native.load("dwmapi", DwmApi.class);
        int DwmSetWindowAttribute(HWND hwnd, int attr, int[] value, int size);
    }

    private boolean applied = false;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!applied && Minecraft.getInstance().getWindow() != null) {
                applied = true;

                try {
                    long glfwWindow = Minecraft.getInstance().getWindow().handle();
                    long hwndLong = GLFWNativeWin32.glfwGetWin32Window(glfwWindow);
                    HWND hwnd = new HWND();

                    hwnd.setPointer(Pointer.createConstant(hwndLong));

                    int DWMWA_USE_IMMERSIVE_DARK_MODE = 20;
                    int[] enabled = {1};

                    DwmApi.INSTANCE.DwmSetWindowAttribute(
                            hwnd,
                            DWMWA_USE_IMMERSIVE_DARK_MODE,
                            enabled,
                            4
                    );

                    System.out.println("Injected Dark Title Bar!");
                } catch (Throwable t) {
                    System.out.println("Dark Title Bar Injection Failed...");
                }
            }
        });
    }
}