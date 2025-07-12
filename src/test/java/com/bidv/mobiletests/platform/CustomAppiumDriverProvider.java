package com.bidv.mobiletests.platform;

import org.openqa.selenium.MutableCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.core.webdriver.driverproviders.DriverProvider;
import net.thucydides.model.util.EnvironmentVariables;

import java.net.URL;

/**
 * Custom Appium driver provider that works with Serenity 4.x and Appium 8.x
 * Uses W3C capabilities format with proper vendor prefixes.
 * 
 * @author bidv
 */
public class CustomAppiumDriverProvider implements DriverProvider {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAppiumDriverProvider.class);
    
    @Override
    public AppiumDriver newInstance(String driverName, EnvironmentVariables environmentVariables) {
        try {
            // Create capabilities from environment variables
            MutableCapabilities capabilities = createCapabilities(environmentVariables);
            
            // Log capabilities
            LOGGER.info("=== CAPABILITIES ===");
            capabilities.asMap().forEach((key, value) -> {
                LOGGER.info("Capability - {}: {}", key, value);
            });
            
            // Get the Appium server URL
            String hubUrl = environmentVariables.getProperty("appium.hub");
            URL serverUrl = new URL(hubUrl);
            
            // Create driver based on platform
            String platformName = environmentVariables.getProperty("appium.platformName");
            
            if ("Android".equalsIgnoreCase(platformName)) {
                return new AndroidDriver(serverUrl, capabilities);
            } else if ("IOS".equalsIgnoreCase(platformName)) {
                return new IOSDriver(serverUrl, capabilities);
            } else {
                return new AppiumDriver(serverUrl, capabilities);
            }
            
        } catch (Exception e) {
            LOGGER.error("Failed to create Appium driver", e);
            throw new RuntimeException("Failed to create Appium driver", e);
        }
    }
    
    /**
     * Create capabilities from environment variables, using W3C format with appium: prefix
     */
    private MutableCapabilities createCapabilities(EnvironmentVariables environmentVariables) {
        MutableCapabilities capabilities = new MutableCapabilities();
        
        // Add platform capabilities with appium: prefix
        addCapabilityIfPresent(capabilities, environmentVariables, "appium.platformName", "appium:platformName");
        addCapabilityIfPresent(capabilities, environmentVariables, "appium.automationName", "appium:automationName");
        addCapabilityIfPresent(capabilities, environmentVariables, "appium.deviceName", "appium:deviceName");
        addCapabilityIfPresent(capabilities, environmentVariables, "appium.platformVersion", "appium:platformVersion");
        addCapabilityIfPresent(capabilities, environmentVariables, "appium.app", "appium:app");
        addCapabilityIfPresent(capabilities, environmentVariables, "appium.udid", "appium:udid");
        addCapabilityIfPresent(capabilities, environmentVariables, "appium.noReset", "appium:noReset");
        addCapabilityIfPresent(capabilities, environmentVariables, "appium.fullReset", "appium:fullReset");
        addCapabilityIfPresent(capabilities, environmentVariables, "appium.newCommandTimeout", "appium:newCommandTimeout");
        
        // Add W3C standard capabilities
        String platformName = environmentVariables.getProperty("appium.platformName");
        if (platformName != null) {
            capabilities.setCapability("platformName", platformName);
        }
        
        return capabilities;
    }
    
    /**
     * Add a capability if the property is present in environment variables
     */
    private void addCapabilityIfPresent(MutableCapabilities capabilities, 
                                       EnvironmentVariables environmentVariables, 
                                       String propertyName, 
                                       String capabilityName) {
        String value = environmentVariables.getProperty(propertyName);
        if (value != null && !value.isEmpty()) {
            // Convert string values to appropriate types
            if ("appium:noReset".equals(capabilityName) || "appium:fullReset".equals(capabilityName)) {
                capabilities.setCapability(capabilityName, Boolean.parseBoolean(value));
            } else if ("appium:newCommandTimeout".equals(capabilityName)) {
                capabilities.setCapability(capabilityName, Integer.parseInt(value));
            } else {
                capabilities.setCapability(capabilityName, value);
            }
        }
    }
} 