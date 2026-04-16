package git.prayoadmii.dark_title_bar.client;

import net.minecraft.client.Minecraft;

import org.lwjgl.glfw.GLFWNativeWin32;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

public class TitleBarController {
    public static void setDark(boolean enabled) {
        try {
            long glfwWindow = Minecraft.getInstance().getWindow().handle();
            long hwndLong = GLFWNativeWin32.glfwGetWin32Window(glfwWindow);

            HWND hwnd = new HWND();

            hwnd.setPointer(Pointer.createConstant(hwndLong));

            int DWMWA_USE_IMMERSIVE_DARK_MODE = 20;

            int[] value = { enabled ? 1 : 0 };

            DwmApi.INSTANCE.DwmSetWindowAttribute(hwnd, DWMWA_USE_IMMERSIVE_DARK_MODE, value, 4);

            System.out.println("[Dark Title Bar For Minecraft]: Title Bar Theme Was Set To " + (enabled ? "Dark" : "Light"));
        } catch (Throwable t) {
            System.out.println("[Dark Title Bar For Minecraft]: Failed To Apply The Theme! (Is It Windows System?)");
        }
    }
}