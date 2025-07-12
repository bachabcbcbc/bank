package com.bank.mobiletests.ui.e_commerce.questions;

import static com.bank.mobiletests.ui.e_commerce.ProductScreen.TERMS_OF_SERVICE_TEXT;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.WebElementQuestion;

/**
 * Question to check if terms of service is visible
 * @author jacob
 */
public class IsTermsOfServiceVisible implements Question<Boolean> {
    
    @Override
    public Boolean answeredBy(Actor actor) {
        return WebElementQuestion.the(TERMS_OF_SERVICE_TEXT).answeredBy(actor)
                .isCurrentlyVisible();
    }

    public static IsTermsOfServiceVisible displayed() {
        return new IsTermsOfServiceVisible();
    }
} 