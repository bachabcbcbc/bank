package com.bank.mobiletests.ui.e_commerce.tasks;

import static com.bank.mobiletests.ui.e_commerce.TabScreen.CATALOG_TAB;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Task to click on the catalog tab
 * @author jacob
 */
public class ClickOnElement implements Task {

    private final Target target;

    public ClickOnElement(Target target) {
        this.target = target;
    }

    public static ClickOnElement now(Target target) {
        return new ClickOnElement(target);
    }
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(target));
    }
} 