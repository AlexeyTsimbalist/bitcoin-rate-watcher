package com.solbegsoft.util;

public class ErrorMessageContext {
    
    private static String message;
    
    public static void writeMessage(String msg) {
        message = msg;
    }
    
    public static void printMessage() {
        if (message != null) {
            CommonActions.printErrorMessage(message);
            message = null;
        }
    }
}
