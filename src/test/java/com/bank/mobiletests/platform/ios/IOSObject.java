package com.bank.mobiletests.platform.ios;

import com.bank.mobiletests.ProviderDriver;

import io.appium.java_client.ios.IOSDriver;
import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

/**
 * A base class representing the interactions of IOSDriver.
 * <br>
 * e.g: getDriver(actor).findElementsByIosUIAutomation(using);
 * 
 * @author jacob
 */
public class IOSObject extends ProviderDriver<IOSDriver> {

    /**
     * Hide the iOS keyboard if it is open.
     */
    public void hideKeyboard(Actor actor) {
        getDriver(actor).hideKeyboard();
    }

    /**
     * Scroll to an element containing the given label (accessibility id).
     */
    public WebElement scrollToLabel(Actor actor, String label) {
        return getDriver(actor).findElement(By.xpath("//*[contains(@label, '" + label + "') or contains(@name, '" + label + "') or contains(@value, '" + label + "') ]"));
    }

    /**
     * Take a screenshot and return it as a byte array.
     */
    public byte[] takeScreenshot(Actor actor) {
        return getDriver(actor).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Tap at specific screen coordinates.
     */
    public void tapByCoordinates(Actor actor, int x, int y) {
        new TouchAction<>(getDriver(actor))
            .tap(PointOption.point(x, y))
            .perform();
    }
}