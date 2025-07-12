package com.bank.serenity.cucumber.stepdefs;

import com.bank.mobiletests.ui.e_commerce.tasks.ClickCartTab;
import com.bank.mobiletests.ui.e_commerce.tasks.ClickCatalogTab;
import com.bank.mobiletests.ui.e_commerce.tasks.ClickMenuTab;
import com.bank.mobiletests.ui.e_commerce.tasks.SwipeUntilVisible;
import com.bank.mobiletests.ui.e_commerce.questions.IsProductTitleVisible;
import com.bank.mobiletests.ui.e_commerce.questions.IsTermsOfServiceVisible;
import com.bank.mobiletests.ui.e_commerce.questions.GetProductTitleText;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static com.bank.mobiletests.ui.e_commerce.ProductScreen.TERMS_OF_SERVICE_TEXT;

import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class ECommerceStepDefs {
	
    @When("{string} clicks on the cart tab")
    public void he_clicks_on_the_cart_tab(String actorName) {
        // First check if product title is visible before clicking cart tab
        theActorCalled(actorName).should(
            seeThat(IsProductTitleVisible.displayed(), is(true))
        );
        
        // Step 2: Scroll to terms of service at bottom
        theActorCalled(actorName).attemptsTo(
            SwipeUntilVisible.to(TERMS_OF_SERVICE_TEXT, 5, SwipeUntilVisible.Direction.DOWN)
        );
    
        // Step 3: Verify terms of service is visible
        theActorCalled(actorName).should(
            seeThat(IsTermsOfServiceVisible.displayed(), is(true))
        );

        // Step 4: Then click on cart tab
        theActorCalled(actorName).attemptsTo(ClickCartTab.now());
    }

    // @When("{string} checks if product title is visible")
    // public void he_checks_product_title_visibility(String actorName) {
    //     theActorCalled(actorName).should(
    //         seeThat(IsProductTitleVisible.displayed(), is(true))
    //     );
    // }

    // @When("{string} scrolls to terms of service")
    // public void he_scrolls_to_terms_of_service(String actorName) {
    //     theActorCalled(actorName).attemptsTo(
    //         ScrollToTermsOfService.now()
    //     );
        
    //     theActorCalled(actorName).should(
    //         seeThat(IsTermsOfServiceVisible.displayed(), is(true))
    //     );
    // }

    // @When("{string} clicks on cart tab after verifying product")
    // public void he_clicks_cart_tab_after_verifying_product(String actorName) {
    //     // Step 1: Check product title is visible
    //     theActorCalled(actorName).should(
    //         seeThat(IsProductTitleVisible.displayed(), is(true))
    //     );
        
    //     // Step 2: Scroll to terms of service at bottom
    //     theActorCalled(actorName).attemptsTo(
    //         ScrollToTermsOfService.now()
    //     );
        
    //     // Step 3: Verify terms of service is visible
    //     theActorCalled(actorName).should(
    //         seeThat(IsTermsOfServiceVisible.displayed(), is(true))
    //     );
        
    //     // Step 4: Click on cart tab
    //     theActorCalled(actorName).attemptsTo(ClickCartTab.now());
    // }

    // @When("{string} clicks on catalog tab")
    // public void he_clicks_on_catalog_tab(String actorName) {
    //     theActorCalled(actorName).attemptsTo(ClickCatalogTab.now());
    // }

    // @When("{string} clicks on menu tab")
    // public void he_clicks_on_menu_tab(String actorName) {
    //     theActorCalled(actorName).attemptsTo(ClickMenuTab.now());
    // }

    // @Then("{string} should see the product title")
    // public void he_should_see_the_product_title(String actorName) {
    //     theActorCalled(actorName).should(
    //         seeThat(IsProductTitleVisible.displayed(), is(true))
    //     );
    // }

    // @Then("the product title should be {string}")
    // public void the_product_title_should_be(String expectedTitle) {
    //     theActorCalled("John").should(
    //         seeThat(GetProductTitleText.text(), is(expectedTitle))
    //     );
    // }
}
