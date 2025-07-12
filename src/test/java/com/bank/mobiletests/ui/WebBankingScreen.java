package com.bank.mobiletests.ui;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class WebBankingScreen {
    
    // Web elements
    public static final Target LOGIN_BUTTON = Target.the("Login button").located(By.id("login-btn"));
    public static final Target USERNAME_FIELD = Target.the("Username field").located(By.id("username"));
    public static final Target PASSWORD_FIELD = Target.the("Password field").located(By.id("password"));
    public static final Target DASHBOARD = Target.the("Dashboard").located(By.id("dashboard"));
    public static final Target TRANSFER_BUTTON = Target.the("Transfer button").located(By.id("transfer-btn"));
    public static final Target TRANSFER_TYPE_DROPDOWN = Target.the("Transfer type dropdown").located(By.id("transfer-type"));
    public static final Target AMOUNT_FIELD = Target.the("Amount field").located(By.id("amount"));
    public static final Target CONFIRM_TRANSFER_BUTTON = Target.the("Confirm transfer button").located(By.id("confirm-transfer"));
    public static final Target ACCOUNT_BALANCE = Target.the("Account balance").located(By.id("account-balance"));
    public static final Target TRANSFER_SUCCESS_MESSAGE = Target.the("Transfer success message").located(By.id("transfer-success"));
    public static final Target CONFIRMATION_MESSAGE = Target.the("Confirmation message").located(By.id("confirmation-msg"));
    public static final Target INSUFFICIENT_FUNDS_ERROR = Target.the("Insufficient funds error").located(By.id("insufficient-funds-error"));
    public static final Target INVALID_AMOUNT_ERROR = Target.the("Invalid amount error").located(By.id("invalid-amount-error"));
    public static final Target ERROR_MESSAGE = Target.the("Error message").located(By.id("error-msg"));
    public static final Target VALIDATION_MESSAGE = Target.the("Validation message").located(By.id("validation-msg"));

    public static Task navigateTo() {
        return Task.where("{0} navigates to web banking",
            Open.url("https://web-banking.example.com")
        );
    }

    public static Task loginWithCredentials(String username, String password) {
        return Task.where("{0} logs in with credentials",
            Enter.theValue(username).into(USERNAME_FIELD),
            Enter.theValue(password).into(PASSWORD_FIELD),
            Click.on(LOGIN_BUTTON)
        );
    }

    public static Task checkAccountBalance() {
        return Task.where("{0} checks account balance",
            Click.on(ACCOUNT_BALANCE)
        );
    }

    public static Task navigateToTransferPage() {
        return Task.where("{0} navigates to transfer page",
            Click.on(TRANSFER_BUTTON)
        );
    }

    public static Task selectTransferType(String transferType) {
        return Task.where("{0} selects transfer type: " + transferType,
            Click.on(TRANSFER_TYPE_DROPDOWN),
            Click.on(Target.the("Transfer type option").located(By.xpath("//option[text()='" + transferType + "']")))
        );
    }

    public static Task enterAmount(String amount) {
        return Task.where("{0} enters amount: " + amount,
            Enter.theValue(amount).into(AMOUNT_FIELD)
        );
    }

    public static Task confirmTransfer() {
        return Task.where("{0} confirms the transfer",
            Click.on(CONFIRM_TRANSFER_BUTTON)
        );
    }

    public static Task expireSession() {
        return Task.where("{0} expires the session",
            // Simulate session expiration by clearing cookies or waiting
            Click.on(Target.the("Logout").located(By.id("logout")))
        );
    }
} 