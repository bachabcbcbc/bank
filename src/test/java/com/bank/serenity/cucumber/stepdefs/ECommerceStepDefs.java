package com.bank.serenity.cucumber.stepdefs;

import com.bank.mobiletests.ui.e_commerce.tasks.CheckElementVisible;
import com.bank.mobiletests.ui.e_commerce.tasks.ClickOnElement;
import com.bank.mobiletests.ui.e_commerce.tasks.SwipeUntilVisible;
import com.bank.mobiletests.ui.e_commerce.questions.ElementIsDisplayed;
import com.bank.mobiletests.ui.e_commerce.questions.ElementIsVisible;
import com.bank.mobiletests.ui.e_commerce.questions.ElementContainText;

import static com.bank.mobiletests.ui.e_commerce.ProductScreen.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static com.bank.mobiletests.ui.e_commerce.TabScreen.CART_TAB;
import static com.bank.mobiletests.ui.e_commerce.CartScreen.NO_ITEMS_CART_TEXT;
import static com.bank.mobiletests.ui.e_commerce.CartScreen.EMPTY_CART_TEXT;
import static com.bank.mobiletests.ui.e_commerce.CartScreen.GO_SHOPPING_BUTTON;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Text;

public class ECommerceStepDefs {

    @When("{string} open app")
    public void he_open_app(String actorName) {
        OnStage.theActorCalled(actorName);
        theActorCalled(actorName).should(
                seeThat(ElementIsVisible.forTarget(PRODUCT_TITLE), is(true))
        );
    }
    @When("{string} clicks on the cart tab")
    public void he_clicks_on_the_cart_tab(String actorName) {
        // First check if product title is visible before clicking cart tab

        theActorCalled(actorName).should(
            seeThat(ElementIsVisible.forTarget(PRODUCT_TITLE), is(true))
        );
        
        // // Step 2: Scroll to terms of service at bottom
        // theActorCalled(actorName).attemptsTo(
        //     SwipeUntilVisible.to(TERMS_OF_SERVICE_TEXT, 5, SwipeUntilVisible.Direction.DOWN)
        // );
    
        // // Step 3: Verify terms of service is visible
        // theActorCalled(actorName).should(
        //     seeThat(ElementContainText.forTarget(TERMS_OF_SERVICE_TEXT, "Terms of Service"), is(true))
        // );

        // Step 4: Then click on cart tab
        theActorCalled(actorName).attemptsTo(ClickOnElement.now(CART_TAB));

        theActorCalled(actorName).should(
            seeThat(ElementContainText.forTarget(NO_ITEMS_CART_TEXT, "No Items"), is(true))
        );
        theActorCalled(actorName).should(
            seeThat(ElementContainText.forTarget(EMPTY_CART_TEXT, "Oh no! Your cart is empty. Fill it up with swag to complete your purchase."), is(true))
        );
        theActorCalled(actorName).should(
            seeThat(ElementContainText.forTarget(GO_SHOPPING_BUTTON, "Go Shopping"), is(true))
        );
        theActorCalled(actorName).attemptsTo(ClickOnElement.now(GO_SHOPPING_BUTTON));

        theActorCalled(actorName).should(
            seeThat(ElementContainText.forTarget(PRODUCT_TITLE, "Products"), is(true))
        );
    }
}
