package com.bidv.mobiletests.platform.ios;

import com.bidv.mobiletests.ProviderDriver;

import io.appium.java_client.ios.IOSDriver;
import net.serenitybdd.screenplay.Actor;

/**
 * A base class representing the interactions of IOSDriver.
 * <br>
 * e.g: getDriver(actor).findElementsByIosUIAutomation(using);
 * 
 * @author jacob
 */
public abstract class IOSObject extends ProviderDriver<IOSDriver> {

    // TouchAction has been deprecated in Appium Java Client 8.x
    // Use W3C Actions API instead (implemented in parent AppiumObject class)
 
}