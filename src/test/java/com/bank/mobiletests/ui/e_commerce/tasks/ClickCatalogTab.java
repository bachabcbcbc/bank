package com.bank.mobiletests.ui.e_commerce.tasks;

import static com.bank.mobiletests.ui.e_commerce.TabScreen.CATALOG_TAB;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

/**
 * Task to click on the catalog tab
 * @author jacob
 */
public class ClickCatalogTab implements Task {
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CATALOG_TAB));
    }

    public static ClickCatalogTab now() {
        return new ClickCatalogTab();
    }
} 