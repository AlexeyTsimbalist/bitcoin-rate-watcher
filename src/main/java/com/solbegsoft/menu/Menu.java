package com.solbegsoft.menu;

public interface Menu {
    
    void display();
    
    default void returnAction() {
        display();
    }
}
