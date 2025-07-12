package com.bank.mobiletests.ui.e_commerce.questions;

import static com.bank.mobiletests.ui.e_commerce.ProductScreen.TERMS_OF_SERVICE_TEXT;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Question to check if terms of service is visible
 * @author jacob
 */
public class ElementIsDisplayed implements Question<Boolean> {
    
    private final Target target;

    public ElementIsDisplayed(Target target) {
        this.target = target;
    }

    public static ElementIsDisplayed forTarget(Target target) {
        return new ElementIsDisplayed(target);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return target.resolveFor(actor).isCurrentlyVisible();
    }
} 