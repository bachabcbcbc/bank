package com.bank.mobiletests.ui.e_commerce;

import org.openqa.selenium.By;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.ByTarget;
import net.serenitybdd.screenplay.targets.Target;

/**
 * @author jacob
 */
public class TabScreen {

    public static final Target CATALOG_TAB = ByTarget
            .the("Catalog tab")
            .locatedForAndroid(By.xpath("//XCUIElementTypeButton[@name='tab bar option catalog']"))
            .locatedForIOS((By.xpath("//XCUIElementTypeButton[@name='tab bar option catalog']")));

    public static final Target CART_TAB = ByTarget
            .the("Cart tab")
            .locatedForAndroid(By.xpath("//XCUIElementTypeButton[@name='tab bar option cart']"))
            .locatedForIOS((By.xpath("//XCUIElementTypeButton[@name='tab bar option cart']")));
     
    public static final Target MENU_TAB = ByTarget
            .the("Menu tab")
            .locatedForAndroid(By.xpath("//XCUIElementTypeButton[@name='tab bar option menu']"))
            .locatedForIOS((By.xpath("//XCUIElementTypeButton[@name='tab bar option menu']")));    
    
    // Click functions for each tab
    public static Task clickCatalogTab() {
        return Task.where("{0} clicks on the catalog tab",
            Click.on(CATALOG_TAB)
        );
    }
    
    public static Task clickCartTab() {
        return Task.where("{0} clicks on the cart tab",
            Click.on(CART_TAB)
        );
    }
    
    public static Task clickMenuTab() {
        return Task.where("{0} clicks on the menu tab",
            Click.on(MENU_TAB)
        );
    }
}
