package com.bank.mobiletests.ui.e_commerce.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class ElementContainText implements Question<Boolean> {
    private final Target target;
    private final String text;

    public ElementContainText(Target target, String text) {
        this.target = target;
        this.text = text;
    }

    public static ElementContainText forTarget(Target target, String text) {
        return new ElementContainText(target, text);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return target.resolveFor(actor).getText().contains(text);
    }
} 