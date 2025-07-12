package com.bank.mobiletests.ui;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class MobileBankingScreen {
    
    // Mobile app elements
    public static final Target APP_ICON = Target.the("App icon").located(By.id("app-icon"));
    public static final Target ACCOUNT_LINKED_STATUS = Target.the("Account linked status").located(By.id("account-linked"));
    public static final Target TRANSFER_NOTIFICATION = Target.the("Transfer notification").located(By.id("transfer-notification"));
    public static final Target APPROVE_TRANSFER_BUTTON = Target.the("Approve transfer button").located(By.id("approve-transfer"));
    public static final Target MOBILE_PIN_FIELD = Target.the("Mobile PIN field").located(By.id("mobile-pin"));
    public static final Target CONFIRM_APPROVAL_BUTTON = Target.the("Confirm approval button").located(By.id("confirm-approval"));
    public static final Target ACCOUNT_BALANCE = Target.the("Account balance").located(By.id("mobile-balance"));
    public static final Target TRANSFER_SUCCESS_MESSAGE = Target.the("Transfer success message").located(By.id("mobile-transfer-success"));
    public static final Target CONFIRMATION_MESSAGE = Target.the("Confirmation message").located(By.id("mobile-confirmation"));
    public static final Target SECURITY_BLOCK_MESSAGE = Target.the("Security block message").located(By.id("security-block"));
    public static final Target REAUTHENTICATION_PROMPT = Target.the("Reauthentication prompt").located(By.id("re-auth-prompt"));

    public static Task verifyAppInstalled() {
        return Task.where("{0} verifies app is installed",
            Click.on(APP_ICON)
        );
    }

    public static Task verifyAccountLinked() {
        return Task.where("{0} verifies account is linked",
            Click.on(ACCOUNT_LINKED_STATUS)
        );
    }

    public static Task approveTransfer() {
        return Task.where("{0} approves the transfer",
            Click.on(APPROVE_TRANSFER_BUTTON)
        );
    }

    public static Task enterMobilePin(String pin) {
        return Task.where("{0} enters mobile PIN: " + pin,
            Enter.theValue(pin).into(MOBILE_PIN_FIELD)
        );
    }

    public static Task confirmApproval() {
        return Task.where("{0} confirms the approval",
            Click.on(CONFIRM_APPROVAL_BUTTON)
        );
    }

    public static Task attemptTransferCompletion() {
        return Task.where("{0} attempts to complete transfer",
            Click.on(APPROVE_TRANSFER_BUTTON)
        );
    }
} 