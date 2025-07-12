package com.bank.mobiletests.ui.e_commerce.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;

public class CheckElementVisible implements Task {
    private final Target target;

    public CheckElementVisible(Target target) {
        this.target = target;
    }

    public static CheckElementVisible forTarget(Target target) {
        return new CheckElementVisible(target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (!target.resolveFor(actor).isVisible()) {
            throw new AssertionError("Element not visible: " + target.getName());
        }
    }
} 