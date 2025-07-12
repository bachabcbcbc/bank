package com.bank.mobiletests.platform.android;

import org.openqa.selenium.MutableCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.webdriver.enhancers.BeforeAWebdriverScenario;
import net.thucydides.model.domain.TestOutcome;
import net.thucydides.model.util.EnvironmentVariables;
import net.thucydides.core.webdriver.SupportedWebDriver;

/**
 * Simple way to extend the Android WebDriver capabilities and customize the driver creation activities.
 * <p>
 * 
 * Usage: Add this properties to android.properties
 * <pre>
 * serenity.extension.packages=com.bank.mobiletests.platform.android
 * </pre>
 * 
 * @author bank
 */
public class AndroidCapabilityEnhancer implements BeforeAWebdriverScenario {

    private static final Logger LOGGER = LoggerFactory.getLogger(AndroidCapabilityEnhancer.class);

    @Override
    public MutableCapabilities apply(EnvironmentVariables environmentVariables,
                                   SupportedWebDriver driver,
                                   TestOutcome testOutcome,
                                   MutableCapabilities capabilities) {
        
        // Create a new capabilities object with proper W3C format
        MutableCapabilities newCaps = new MutableCapabilities();
        
        // Add platform capabilities with appium: prefix
        newCaps.setCapability("appium:platformName", "Android");
        newCaps.setCapability("appium:automationName", "UiAutomator2");
        
        // Add device capabilities
        String deviceName = environmentVariables.getProperty("appium.deviceName");
        if (deviceName != null) {
            newCaps.setCapability("appium:deviceName", deviceName);
        }
        
        String platformVersion = environmentVariables.getProperty("appium.platformVersion");
        if (platformVersion != null) {
            newCaps.setCapability("appium:platformVersion", platformVersion);
        }
        
        // Add app capability
        String app = environmentVariables.getProperty("appium.app");
        if (app != null) {
            newCaps.setCapability("appium:app", app);
        }
        
        // Add other Android specific capabilities
        newCaps.setCapability("appium:noReset", true);
        newCaps.setCapability("appium:fullReset", false);
        newCaps.setCapability("appium:newCommandTimeout", 120);
        newCaps.setCapability("appium:autoGrantPermissions", true);
        newCaps.setCapability("appium:skipServerInstallation", false);
        newCaps.setCapability("appium:skipDeviceInitialization", false);
        
        // Add W3C standard capabilities
        newCaps.setCapability("platformName", "Android");
        
        // Log the capabilities for debugging
        LOGGER.info("Android Capabilities: {}", newCaps.asMap());
        
        return newCaps;
    }
}
