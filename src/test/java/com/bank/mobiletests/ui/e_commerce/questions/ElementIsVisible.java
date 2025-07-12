package com.bank.mobiletests.ui.e_commerce.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class ElementIsVisible implements Question<Boolean> {
    private final Target target;

    public ElementIsVisible(Target target) {
        this.target = target;
    }

    public static ElementIsVisible forTarget(Target target) {
        return new ElementIsVisible(target);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return target.resolveFor(actor).isVisible();
    }
} 