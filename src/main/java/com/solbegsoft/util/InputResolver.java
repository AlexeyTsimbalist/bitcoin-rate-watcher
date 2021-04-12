package com.solbegsoft.util;

import static com.solbegsoft.util.Constants.NO_SUCH_OPTION;

public class InputResolver {
    
    public void resolveOption(int option, ActionHolder actionHolder, Runnable returnAction) {
        try {
            actionHolder.get(option).run();
        } catch (NullPointerException npe) {
            ErrorMessageContext.writeMessage(NO_SUCH_OPTION);
            returnAction.run();
        }
    }
    
    public boolean resolveCurrency(String currency) {
        return currency != null && !currency.trim().isEmpty();
    }
}
