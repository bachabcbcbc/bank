//package com.bank.mobiletests.platform;
//
//import org.openqa.selenium.MutableCapabilities;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.android.AndroidDriver;
//import net.serenitybdd.core.webdriver.driverproviders.DriverProvider;
//import net.thucydides.model.util.EnvironmentVariables;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.Properties;
//import java.util.List;
//import java.util.ArrayList;
//
//
///**
// * Custom Appium driver provider that works with Serenity 4.x and Appium 8.x
// * Uses W3C capabilities format with proper vendor prefixes.
// *
// * @author bank
// */
//public class CustomAppiumDriverProvider implements DriverProvider {
//    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAppiumDriverProvider.class);
//
//    @Override
//    public AppiumDriver newInstance(String driverName, EnvironmentVariables environmentVariables) {
//        try {
//            // Lấy danh sách các file properties từ command line (như android.properties, android1.properties)
//            String propertiesList = environmentVariables.getProperty("properties");
//            String[] propertiesFiles = propertiesList.split(",");
//
//            // Tạo danh sách các drivers
//            List<AppiumDriver> drivers = new ArrayList<>();
//
//            for (String propertiesFile : propertiesFiles) {
//                // Tạo capabilities từ file properties
//                MutableCapabilities capabilities = createCapabilitiesFromFile(propertiesFile, environmentVariables);
//
//                // Log capabilities
//                LOGGER.info("=== CAPABILITIES ===");
//                capabilities.asMap().forEach((key, value) -> {
//                    LOGGER.info("Capability - {}: {}", key, value);
//                });
//
//                // Lấy URL của Appium Hub
//                String hubUrl = environmentVariables.getProperty("appium.hub");
//                URL serverUrl = new URL(hubUrl);
//
//                // Lấy tên nền tảng (Android/iOS)
//                String platformName = environmentVariables.getProperty("appium.platformName");
//
//                // Tạo driver cho từng nền tảng
//                if ("Android".equalsIgnoreCase(platformName)) {
//                    drivers.add(new AndroidDriver(serverUrl, capabilities));
//                } else if ("IOS".equalsIgnoreCase(platformName)) {
//                    drivers.add(new IOSDriver(serverUrl, capabilities));
//                } else {
//                    drivers.add(new AppiumDriver(serverUrl, capabilities));
//                }
//            }
//
//            // Quay lại driver đầu tiên (hoặc bạn có thể return danh sách driver nếu cần)
//            return drivers.isEmpty() ? null : drivers.get(0);
//
//        } catch (Exception e) {
//            LOGGER.error("Failed to create Appium driver", e);
//            throw new RuntimeException("Failed to create Appium driver", e);
//        }
//    }
//
//    private MutableCapabilities createCapabilitiesFromFile(String propertiesFile, EnvironmentVariables environmentVariables) {
//        // Load the properties file and merge it with environment variables
//        MutableCapabilities capabilities = new MutableCapabilities();
//
//        // Đọc các properties từ file cấu hình
//        Properties properties = new Properties();
//        try (InputStream input = new FileInputStream("src/test/resources/" + propertiesFile)) {
//            properties.load(input);
//        } catch (IOException ex) {
//            LOGGER.error("Error loading properties file", ex);
//        }
//
//        // Thêm các capabilities từ properties
//        addCapabilitiesFromProperties(properties, capabilities);
//
//        return capabilities;
//    }
//
//    /**
//     * Thêm các capabilities từ file properties vào MutableCapabilities
//     */
//    private void addCapabilitiesFromProperties(Properties properties, MutableCapabilities capabilities) {
//        // Thêm tất cả các properties vào capabilities
//        properties.forEach((key, value) -> {
//            if (key != null && value != null) {
//                capabilities.setCapability((String) key, value);
//            }
//        });
//    }
//
//    /**
//     * Create capabilities from environment variables, using W3C format with appium: prefix
//     */
//    private MutableCapabilities createCapabilities(EnvironmentVariables environmentVariables) {
//        MutableCapabilities capabilities = new MutableCapabilities();
//
//        // Add platform capabilities with appium: prefix
//        addCapabilityIfPresent(capabilities, environmentVariables, "appium.platformName", "appium:platformName");
//        addCapabilityIfPresent(capabilities, environmentVariables, "appium.automationName", "appium:automationName");
//        addCapabilityIfPresent(capabilities, environmentVariables, "appium.deviceName", "appium:deviceName");
//        addCapabilityIfPresent(capabilities, environmentVariables, "appium.platformVersion", "appium:platformVersion");
//        addCapabilityIfPresent(capabilities, environmentVariables, "appium.app", "appium:app");
//        addCapabilityIfPresent(capabilities, environmentVariables, "appium.udid", "appium:udid");
//        addCapabilityIfPresent(capabilities, environmentVariables, "appium.bundleId", "appium:bundleId");
//        addCapabilityIfPresent(capabilities, environmentVariables, "appium.noReset", "appium:noReset");
//        addCapabilityIfPresent(capabilities, environmentVariables, "appium.fullReset", "appium:fullReset");
//        addCapabilityIfPresent(capabilities, environmentVariables, "appium.newCommandTimeout", "appium:newCommandTimeout");
//
//        // Add W3C standard capabilities
//        String platformName = environmentVariables.getProperty("appium.platformName");
//        if (platformName != null) {
//            capabilities.setCapability("platformName", platformName);
//        }
//
//        return capabilities;
//    }
//
//    /**
//     * Add a capability if the property is present in environment variables
//     */
//    private void addCapabilityIfPresent(MutableCapabilities capabilities,
//                                       EnvironmentVariables environmentVariables,
//                                       String propertyName,
//                                       String capabilityName) {
//        String value = environmentVariables.getProperty(propertyName);
//        if (value != null && !value.isEmpty()) {
//            // Convert string values to appropriate types
//            if ("appium:noReset".equals(capabilityName) || "appium:fullReset".equals(capabilityName)) {
//                capabilities.setCapability(capabilityName, Boolean.parseBoolean(value));
//            } else if ("appium:newCommandTimeout".equals(capabilityName)) {
//                capabilities.setCapability(capabilityName, Integer.parseInt(value));
//            } else {
//                capabilities.setCapability(capabilityName, value);
//            }
//        }
//    }
//}