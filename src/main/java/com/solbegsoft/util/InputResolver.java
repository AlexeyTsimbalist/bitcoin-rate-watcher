package com.solbegsoft.util;

import static com.solbegsoft.util.Constants.ERROR_HAS_OCCURRED;
import static com.solbegsoft.util.Constants.NO_SUCH_OPTION;

public class InputResolver {
    
    public void resolveOption(int option, ActionHolder actionHolder) {
        if (option > 0) {
            try {
                actionHolder.get(option).run();
            } catch (NullPointerException npe) {
                ErrorMessageContext.writeMessage(NO_SUCH_OPTION);
                actionHolder.get(-1).run();
            }
        } else {
            ErrorMessageContext.writeMessage(ERROR_HAS_OCCURRED);
            actionHolder.get(-1).run();
        }
    }
    
    public boolean resolveCurrency(String currency) {
        return currency != null && !currency.trim().isEmpty();
    }
}
