package com.bank.mobiletests.ui.e_commerce.tasks;

import static com.bank.mobiletests.ui.e_commerce.TabScreen.CART_TAB;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

/**
 * Task to click on the cart tab
 * @author jacob
 */
public class ClickCartTab implements Task {
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CART_TAB));
    }

    public static ClickCartTab now() {
        return new ClickCartTab();
    }
} 