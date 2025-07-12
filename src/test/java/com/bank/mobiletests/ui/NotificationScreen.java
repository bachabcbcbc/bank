package com.bank.mobiletests.ui;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class NotificationScreen {
    
    // Notification elements
    public static final Target TRANSFER_NOTIFICATION = Target.the("Transfer notification").located(By.id("transfer-notification"));
    public static final Target NOTIFICATION_TITLE = Target.the("Notification title").located(By.id("notification-title"));
    public static final Target NOTIFICATION_BODY = Target.the("Notification body").located(By.id("notification-body"));

    public static Task waitForTransferNotification() {
        return Task.where("{0} waits for transfer notification",
            // Wait for notification to appear
            Click.on(TRANSFER_NOTIFICATION)
        );
    }

    public static Task tapOnTransferNotification() {
        return Task.where("{0} taps on transfer notification",
            Click.on(TRANSFER_NOTIFICATION)
        );
    }
} 