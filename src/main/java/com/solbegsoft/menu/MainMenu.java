package com.solbegsoft.menu;

import com.solbegsoft.util.ActionHolder;
import com.solbegsoft.util.CommonActions;
import com.solbegsoft.util.ErrorMessageContext;
import com.solbegsoft.util.ScreenUtils;

import java.util.Scanner;

import static com.solbegsoft.util.Constants.CHOOSE_THE_OPERATION;
import static com.solbegsoft.util.Constants.ERROR_HAS_OCCURRED;

public class MainMenu extends AbstractMenu {
    
    private static final String GET_BITCOIN_RATE = "1. Get Bitcoin rate";
    private static final String EXIT = "2. Exit";
    
    private final BitcoinRateMenu bitcoinRateMenu = new BitcoinRateMenu();
    
    public MainMenu() {
        initCommands();
    }
    
    @Override
    public void display() {
        ScreenUtils.clearScreen();
        ErrorMessageContext.printMessage();
        
        System.out.println(CHOOSE_THE_OPERATION);
        System.out.println(GET_BITCOIN_RATE);
        System.out.println(EXIT);
        
        try {
            Scanner scanner = new Scanner(System.in);
            
            if (scanner.hasNextInt()) {
                inputResolver.resolveOption(scanner.nextInt(), commands, this::returnAction);
            } else {
                ErrorMessageContext.writeMessage(ERROR_HAS_OCCURRED);
                display();
            }
        } catch (Exception exception) {
            ErrorMessageContext.writeMessage(ERROR_HAS_OCCURRED);
            display();
        }
    }
    
    private void initCommands() {
        commands = new ActionHolder();
        
        commands.put(1, bitcoinRateMenu::display);
        commands.put(2, CommonActions::exitApplication);
    }
}
