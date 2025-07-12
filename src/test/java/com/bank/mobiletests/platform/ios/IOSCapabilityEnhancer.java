package com.bank.mobiletests.platform.ios;

import org.openqa.selenium.MutableCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.webdriver.enhancers.BeforeAWebdriverScenario;
import net.thucydides.model.domain.TestOutcome;
import net.thucydides.model.util.EnvironmentVariables;
import net.thucydides.core.webdriver.SupportedWebDriver;

/**
 * Simple way to extend the iOS WebDriver capabilities and customise the driver creation activities.
 * <p>
 * 
 * Usage: Add this properties to ios.properties
 * <pre>
 * serenity.extension.packages=com.bank.mobiletests.platform.ios
 * </pre>
 * 
 * @author bank
 */
public class IOSCapabilityEnhancer implements BeforeAWebdriverScenario {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOSCapabilityEnhancer.class);

    @Override
    public MutableCapabilities apply(EnvironmentVariables environmentVariables,
                                   SupportedWebDriver driver,
                                   TestOutcome testOutcome,
                                   MutableCapabilities capabilities) {
        
        // Create a new capabilities object with proper W3C format
        MutableCapabilities newCaps = new MutableCapabilities();
        
        // Add platform capabilities with appium: prefix
        newCaps.setCapability("appium:platformName", "IOS");
        newCaps.setCapability("appium:automationName", "XCUITest");
        
        // Add device capabilities
        String deviceName = environmentVariables.getProperty("appium.deviceName");
        if (deviceName != null) {
            newCaps.setCapability("appium:deviceName", deviceName);
        }
        
        String platformVersion = environmentVariables.getProperty("appium.platformVersion");
        if (platformVersion != null) {
            newCaps.setCapability("appium:platformVersion", platformVersion);
        }
        
        String udid = environmentVariables.getProperty("appium.udid");
        if (udid != null) {
            newCaps.setCapability("appium:udid", udid);
        }
        
        // Add app capability
        String app = environmentVariables.getProperty("appium.app");
        if (app != null) {
            newCaps.setCapability("appium:app", app);
        }
        
        // Add other iOS specific capabilities
        newCaps.setCapability("appium:noReset", true);
        newCaps.setCapability("appium:fullReset", false);
        newCaps.setCapability("appium:newCommandTimeout", 120);
        newCaps.setCapability("appium:autoAcceptAlerts", true);
        newCaps.setCapability("appium:autoDismissAlerts", true);
        
        // Add W3C standard capabilities
        newCaps.setCapability("platformName", "IOS");
        
        // Log the capabilities for debugging
        LOGGER.info("iOS Capabilities: {}", newCaps.asMap());
        
        return newCaps;
    }
}
