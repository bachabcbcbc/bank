package com.bank.mobiletests.ui.e_commerce;

import org.openqa.selenium.By;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.targets.ByTarget;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Product screen elements and actions
 * @author jacob
 */
public class ProductScreen {

    public static final Target PRODUCT_TITLE = ByTarget
            .the("Product title")
            .locatedForAndroid(By.id("product_title"))
            .locatedForIOS((By.xpath("//XCUIElementTypeStaticText[@name='Products']")));

    public static final Target TERMS_OF_SERVICE_TEXT = ByTarget
            .the("Terms of service text")
            .locatedForAndroid(By.id("terms_of_service"))
            .locatedForIOS(By.xpath("//XCUIElementTypeImage[@name='assets/src/assets/images/linkedin.png']"));

    // Check if product title is visible
    public static Task checkProductTitleVisible() {
        return Task.where("{0} checks if product title is visible");
    }
    
    // Scroll to terms of service
    public static Task scrollToTermsOfService() {
        return Task.where("{0} scrolls to terms of service",
            Scroll.to(TERMS_OF_SERVICE_TEXT)
        );
    }
    
} 