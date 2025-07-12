package com.bank.mobiletests.platform.android;

import com.bank.mobiletests.ProviderDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

/**
 * A base class representing the interactions of AndroidDriver.<br>
 * 
 * e.g: getDriver(actor).findElementsByAndroidUIAutomator(using)
 * 
 * @author jacob
 */
public class AndroidObject extends ProviderDriver<AndroidDriver> {

    /**
     * Hide the Android keyboard if it is open.
     */
    public void hideKeyboard(Actor actor) {
        getDriver(actor).hideKeyboard();
    }

    /**
     * Scroll to an element containing the given text.
     */
    @SuppressWarnings("deprecation")
    public WebElement scrollToText(Actor actor, String text) {
        return getDriver(actor).findElement(
            MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"
            )
        );
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
    @SuppressWarnings("deprecation")
    public void tapByCoordinates(Actor actor, int x, int y) {
        new io.appium.java_client.TouchAction<>(getDriver(actor))
            .tap(io.appium.java_client.touch.offset.PointOption.point(x, y))
            .perform();
    }
}
