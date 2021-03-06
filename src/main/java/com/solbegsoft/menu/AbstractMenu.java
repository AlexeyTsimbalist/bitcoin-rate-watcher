package com.solbegsoft.menu;

import com.solbegsoft.util.ActionHolder;
import com.solbegsoft.util.InputResolver;

public abstract class AbstractMenu implements Menu {
    protected ActionHolder commands = new ActionHolder();
    protected InputResolver inputResolver = new InputResolver();
}
