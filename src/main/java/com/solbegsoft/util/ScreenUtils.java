package com.solbegsoft.util;

public class ScreenUtils {
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
