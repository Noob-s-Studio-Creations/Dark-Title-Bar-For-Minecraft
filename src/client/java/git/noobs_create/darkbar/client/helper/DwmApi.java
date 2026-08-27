package git.noobs_create.darkbar.client.helper;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;

public interface DwmApi extends Library {
    DwmApi INSTANCE = Native.load("dwmapi", DwmApi.class);

    int DwmSetWindowAttribute(HWND hwnd, int attr, int[] value, int size);
}