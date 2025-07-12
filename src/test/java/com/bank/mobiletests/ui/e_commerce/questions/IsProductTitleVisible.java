package com.bank.mobiletests.ui.e_commerce.questions;

import static com.bank.mobiletests.ui.e_commerce.ProductScreen.PRODUCT_TITLE;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bank.mobiletests.platform.AppiumObject;


/**
 * Question to check if product title is visible
 * @author jacob
 */
public class IsProductTitleVisible implements Question<Boolean> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppiumObject.class);
    @Override
    public Boolean answeredBy(Actor actor) {
        return WebElementQuestion.the(PRODUCT_TITLE).answeredBy(actor)
                .isCurrentlyVisible();
    }

    public static IsProductTitleVisible displayed() {
        LOGGER.info("IsProductTitleVisible displayed");
        return new IsProductTitleVisible();
    }
} 