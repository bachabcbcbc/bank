package com.bank.mobiletests.ui.e_commerce.tasks;

import static com.bank.mobiletests.ui.e_commerce.TabScreen.MENU_TAB;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

/**
 * Task to click on the menu tab
 * @author jacob
 */
public class ClickMenuTab implements Task {
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(MENU_TAB));
    }

    public static ClickMenuTab now() {
        return new ClickMenuTab();
    }
} 