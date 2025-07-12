package com.bidv.mobiletests.platform.android;

import com.bidv.mobiletests.ProviderDriver;

import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.screenplay.Actor;

/**
 * A base class representing the interactions of AndroidDriver.<br>
 * 
 * e.g: getDriver(actor).findElementsByAndroidUIAutomator(using)
 * 
 * @author jacob
 */
public class AndroidObject extends ProviderDriver<AndroidDriver> {

    // TouchAction has been deprecated in Appium Java Client 8.x
    // Use W3C Actions API instead (implemented in parent AppiumObject class)

}
