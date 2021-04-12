package com.solbegsoft.menu;

import com.solbegsoft.service.BitcoinRateService;
import com.solbegsoft.util.ActionHolder;
import com.solbegsoft.util.CommonActions;
import com.solbegsoft.util.ErrorMessageContext;
import com.solbegsoft.util.ScreenUtils;
import org.json.JSONException;

import java.util.Scanner;

import static com.solbegsoft.util.Constants.*;

public class BitcoinRateMenu extends AbstractMenu {
    
    public static final String ENTER_THE_CURRENCY = "Enter the currency you interested in: ";
    private static final String RETRY = "1. Retry";
    private static final String EXIT = "2. Exit";
    
    private final BitcoinRateService service = new BitcoinRateService();
    
    public BitcoinRateMenu() {
        initCommands();
    }
    
    @Override
    public void display() {
        ScreenUtils.clearScreen();
        ErrorMessageContext.printMessage();
        
        try {
            System.out.println(ENTER_THE_CURRENCY);
            
            Scanner scanner = new Scanner(System.in);
            
            if (scanner.hasNextLine()) {
                String currency = scanner.nextLine();
                
                if (inputResolver.resolveCurrency(currency)) {
                    System.out.println(service.fetchInfoByCurrency(currency));
                    
                    chooseOperation();
                } else {
                    ErrorMessageContext.writeMessage(NOT_EMPTY_INPUT);
                    display();
                }
            } else {
                ErrorMessageContext.writeMessage(ERROR_HAS_OCCURRED);
                display();
            }
        } catch (JSONException exception) {
            ErrorMessageContext.writeMessage(NO_SUCH_CURRENCY);
            display();
        } catch (Exception exception) {
            ErrorMessageContext.writeMessage(ERROR_HAS_OCCURRED);
            display();
        }
    }
    
    public void chooseOperation() {
        ErrorMessageContext.printMessage();
        
        System.out.println(CHOOSE_THE_OPERATION);
        System.out.println(RETRY);
        System.out.println(EXIT);
        
        Scanner intScanner = new Scanner(System.in);
        
        if (intScanner.hasNextInt()) {
            inputResolver.resolveOption(intScanner.nextInt(), commands, this::returnAction);
        } else {
            ErrorMessageContext.writeMessage(ERROR_HAS_OCCURRED);
            returnAction();
        }
    }
    
    private void initCommands() {
        commands = new ActionHolder();
        
        commands.put(1, this::display);
        commands.put(2, CommonActions::exitApplication);
    }
    
    @Override
    public void returnAction() {
        chooseOperation();
    }
}