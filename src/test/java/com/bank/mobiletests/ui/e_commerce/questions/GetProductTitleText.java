package com.bank.mobiletests.ui.e_commerce.questions;

import static com.bank.mobiletests.ui.e_commerce.ProductScreen.PRODUCT_TITLE;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/**
 * Question to get the product title text
 * @author jacob
 */
public class GetProductTitleText implements Question<String> {
    
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(PRODUCT_TITLE).answeredBy(actor);
    }

    public static GetProductTitleText text() {
        return new GetProductTitleText();
    }
} 