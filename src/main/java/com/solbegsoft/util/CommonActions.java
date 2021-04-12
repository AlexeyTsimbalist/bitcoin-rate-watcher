package com.solbegsoft.util;

public class CommonActions {
    
    public static void exitApplication() {
        System.out.println("Bye!");
        
        System.exit(0);
    }
    
    public static void printErrorMessage(String message) {
        System.out.println(message);
        System.out.println();
    }
}
