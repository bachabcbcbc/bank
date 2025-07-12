package com.bank.serenity.cucumber.stepdefs;


import com.bank.mobiletests.ui.WebBankingScreen;
import com.bank.mobiletests.ui.MobileBankingScreen;
import com.bank.mobiletests.ui.NotificationScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class MoneyTransferStepDefs {

    private Actor john;
    private WebDriver webDriver;

    @Given("{string} is logged into the web banking application")
    public void userIsLoggedIntoWebBanking(String userName) {
        john = Actor.named(userName).whoCan(BrowseTheWeb.with(webDriver));
        webDriver = BrowseTheWeb.as(john).getDriver();
        
        // Navigate to web banking and login
        john.attemptsTo(
            WebBankingScreen.navigateTo(),
            WebBankingScreen.loginWithCredentials("john@example.com", "password123")
        );
        
        john.should(seeThat(the(WebBankingScreen.DASHBOARD), isVisible()));
    }

    @Given("{string} has a linked mobile banking app")
    public void userHasLinkedMobileApp(String userName) {
        // Verify mobile app is installed and linked
        john.attemptsTo(
            MobileBankingScreen.verifyAppInstalled(),
            MobileBankingScreen.verifyAccountLinked()
        );
    }

    @Given("{string} has sufficient funds in his account")
    public void userHasSufficientFunds(String userName) {
        john.attemptsTo(
            WebBankingScreen.checkAccountBalance(),
            Ensure.that(WebBankingScreen.ACCOUNT_BALANCE).text().isGreaterThan("0")
        );
    }

    @When("{string} initiates a transfer of {string} from web to mobile app")
    public void userInitiatesTransfer(String userName, String amount) {
        john.attemptsTo(
            WebBankingScreen.navigateToTransferPage(),
            WebBankingScreen.selectTransferType("WEB_TO_MOBILE"),
            WebBankingScreen.enterAmount(amount),
            WebBankingScreen.confirmTransfer()
        );
    }

    @When("{string} receives a notification on his mobile app")
    public void userReceivesMobileNotification(String userName) {
        john.attemptsTo(
            NotificationScreen.waitForTransferNotification(),
            Ensure.that(NotificationScreen.TRANSFER_NOTIFICATION).isDisplayed()
        );
    }

    @When("{string} approves the transfer on his mobile app")
    public void userApprovesTransferOnMobile(String userName) {
        john.attemptsTo(
            NotificationScreen.tapOnTransferNotification(),
            MobileBankingScreen.approveTransfer(),
            MobileBankingScreen.enterMobilePin("1234"),
            MobileBankingScreen.confirmApproval()
        );
    }

    @Then("the transfer should be completed successfully")
    public void transferShouldBeCompleted() {
        john.should(
            seeThat(the(WebBankingScreen.TRANSFER_SUCCESS_MESSAGE), isVisible()),
            seeThat(the(MobileBankingScreen.TRANSFER_SUCCESS_MESSAGE), isVisible())
        );
    }

    @Then("{string} should see the updated balance on both web and mobile")
    public void userShouldSeeUpdatedBalance(String userName) {
        String webBalance = Text.of(WebBankingScreen.ACCOUNT_BALANCE).answeredBy(john);
        String mobileBalance = Text.of(MobileBankingScreen.ACCOUNT_BALANCE).answeredBy(john);
        
        // Verify balances are updated and consistent
        john.attemptsTo(
            Ensure.that(webBalance).isNotEqualTo("$1000"), // Assuming initial balance
            Ensure.that(mobileBalance).isEqualTo(webBalance)
        );
    }

    @Then("a confirmation message should be displayed")
    public void confirmationMessageShouldBeDisplayed() {
        john.should(
            seeThat(the(WebBankingScreen.CONFIRMATION_MESSAGE), isVisible()),
            seeThat(the(MobileBankingScreen.CONFIRMATION_MESSAGE), isVisible())
        );
    }

    @When("{string} attempts to transfer {string} from web to mobile app")
    public void userAttemptsTransfer(String userName, String amount) {
        john.attemptsTo(
            WebBankingScreen.navigateToTransferPage(),
            WebBankingScreen.selectTransferType("WEB_TO_MOBILE"),
            WebBankingScreen.enterAmount(amount),
            WebBankingScreen.confirmTransfer()
        );
    }

    @Then("the transfer should be rejected due to insufficient funds")
    public void transferShouldBeRejectedForInsufficientFunds() {
        john.should(
            seeThat(the(WebBankingScreen.INSUFFICIENT_FUNDS_ERROR), isVisible())
        );
    }

    @Then("an appropriate error message should be displayed")
    public void appropriateErrorMessageShouldBeDisplayed() {
        john.should(
            seeThat(the(WebBankingScreen.ERROR_MESSAGE), isVisible())
        );
    }

    @Then("the transfer should be rejected due to invalid amount")
    public void transferShouldBeRejectedForInvalidAmount() {
        john.should(
            seeThat(the(WebBankingScreen.INVALID_AMOUNT_ERROR), isVisible())
        );
    }

    @Then("an appropriate validation message should be displayed")
    public void appropriateValidationMessageShouldBeDisplayed() {
        john.should(
            seeThat(the(WebBankingScreen.VALIDATION_MESSAGE), isVisible())
        );
    }

    @When("{string}'s web session expires during transfer")
    public void userWebSessionExpires(String userName) {
        // Simulate session expiration
        john.attemptsTo(
            WebBankingScreen.expireSession()
        );
    }

    @When("{string} tries to complete the transfer on mobile")
    public void userTriesToCompleteTransferOnMobile(String userName) {
        john.attemptsTo(
            MobileBankingScreen.attemptTransferCompletion()
        );
    }

    @Then("the transfer should be blocked for security reasons")
    public void transferShouldBeBlockedForSecurity() {
        john.should(
            seeThat(the(MobileBankingScreen.SECURITY_BLOCK_MESSAGE), isVisible())
        );
    }

    @Then("{string} should be prompted to re-authenticate")
    public void userShouldBePromptedToReAuthenticate(String userName) {
        john.should(
            seeThat(the(MobileBankingScreen.REAUTHENTICATION_PROMPT), isVisible())
        );
    }
} 