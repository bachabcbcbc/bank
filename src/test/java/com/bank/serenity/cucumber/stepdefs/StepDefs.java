package com.bank.serenity.cucumber.stepdefs;

import static com.bank.mobiletests.ui.DashboardScreen.CLOSE_ALERT;
import static com.bank.mobiletests.ui.DashboardScreen.ADD_BUTTON;
import static com.bank.mobiletests.ui.NewNoteScreen.DETAIL_DESCRIPTION;
import static com.bank.mobiletests.ui.NewNoteScreen.DETAIL_TITLE;
import static com.bank.mobiletests.ui.NewNoteScreen.INPUT_DESCRIPTION;
import static com.bank.mobiletests.ui.NewNoteScreen.INPUT_TITLE;
import static com.bank.mobiletests.ui.NewNoteScreen.SAVE_BUTTON;
import com.bank.serenity.utils.Util;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;

public class StepDefs {
	
    @When("{string} notes {string} to his list")
    public void he_notes_to_his_list(String actorName, String item) {
        theActorCalled(actorName).attemptsTo(
            Check.whether(actor -> CLOSE_ALERT.resolveFor(actor).isVisible()).andIfSo(Click.on(CLOSE_ALERT))
        );
        theActorCalled(actorName).attemptsTo(Click.on(ADD_BUTTON));
    }

    @When("{string} creates a new note with title {string} and description {string}")
    public void he_creates_a_new_note_with_title_and_description(String actorName, String title, String description) {
        theActorCalled(actorName).attemptsTo(Click.on(ADD_BUTTON));
        theActorCalled(actorName).attemptsTo(Enter.theValue(title).into(INPUT_TITLE));
        theActorCalled(actorName).attemptsTo(Enter.theValue(description).into(INPUT_DESCRIPTION));
        theActorCalled(actorName).attemptsTo(Click.on(SAVE_BUTTON));
    }

    @Then("{string} should see the note with title {string} and description {string}")
    public void he_should_see_the_note_with_title_and_description(String actorName, String title, String description) {
        Util.sleep(5000);
        theActorCalled(actorName).should(
            seeThat("last title is correct", 
                actor -> {
                    java.util.List<net.serenitybdd.core.pages.WebElementFacade> elements = DETAIL_TITLE.resolveAllFor(actor);
                    return elements.get(elements.size() - 1).getText();
                }, equalTo(title)),
            seeThat("last description is correct", 
                actor -> {
                    java.util.List<net.serenitybdd.core.pages.WebElementFacade> elements = DETAIL_DESCRIPTION.resolveAllFor(actor);
                    return elements.get(elements.size() - 1).getText();
                }, equalTo(description))
        );
        Util.sleep(5000);
    }
}
