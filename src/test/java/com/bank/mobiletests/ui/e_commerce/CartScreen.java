package com.bank.mobiletests.ui.e_commerce;

import org.openqa.selenium.By;

import net.serenitybdd.screenplay.targets.ByTarget;
import net.serenitybdd.screenplay.targets.Target;

/**
 * @author jacob
 */
public class CartScreen {

    public static final Target NO_ITEMS_CART_TEXT = ByTarget
            .the("No items")
            .locatedForAndroid(By.xpath("//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView"))
            .locatedForIOS((By.xpath("//XCUIElementTypeStaticText[@name='No Items']")));

    public static final Target EMPTY_CART_TEXT = ByTarget
            .the("Empty cart text")
            .locatedForAndroid(By.xpath("//android.widget.ScrollView[@content-desc='cart screen']/android.view.ViewGroup/android.widget.TextView"))
            .locatedForIOS((By.xpath("//XCUIElementTypeStaticText[@name='Oh no! Your cart is empty. Fill it up with swag to complete your purchase.']")));
     
    public static final Target GO_SHOPPING_BUTTON = ByTarget
            .the("Go Shopping button")
            .locatedForAndroid(By.xpath("//android.view.ViewGroup[@content-desc='Go Shopping button']/android.widget.TextView"))
            .locatedForIOS((By.xpath("//XCUIElementTypeOther[@name='Go Shopping button']")));    
    
}
