package com.solbegsoft.util;

import static com.solbegsoft.util.Constants.ERROR_HAS_OCCURRED;

public class CommonActions {
    
    public static void exitApplication() {
        System.out.println("Bye!");
        
        System.exit(0);
    }
    
    public static void printErrorMessage() {
        System.out.println(ERROR_HAS_OCCURRED);
        System.out.println();
    }
    
    public static void printErrorMessage(String message) {
        System.out.println(message);
        System.out.println();
    }
}
