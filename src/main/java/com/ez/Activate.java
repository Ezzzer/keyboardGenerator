package com.ez;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.W32APIOptions;

public class Activate {


    public interface User32 extends W32APIOptions {

        User32 instance = (User32) Native.loadLibrary("user32", User32.class,
                DEFAULT_OPTIONS);


        boolean ShowWindow(HWND hWnd, int nCmdShow);

        boolean SetForegroundWindow(HWND hWnd);

        HWND FindWindow(String winClass, String title);

        int SW_SHOW = 1;

    }

    public static void setFocus() {
        User32 user32 = User32.instance;
        HWND hWnd = user32.FindWindow(null, "MilkDrop 2");
        user32.ShowWindow(hWnd, User32.SW_SHOW);
        user32.SetForegroundWindow(hWnd);
    }
}