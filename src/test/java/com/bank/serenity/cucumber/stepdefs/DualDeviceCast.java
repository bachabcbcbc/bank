package com.bank.serenity.cucumber.stepdefs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DualDeviceCast extends Cast {

    @Override
    public Actor actorNamed(String actorName, Ability... abilities) {
        WebDriver driver = null;
        try {
            driver = createDriverFor(actorName);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return super.actorNamed(actorName, BrowseTheWeb.with(driver));
    }

    public WebDriver createDriverFor(String actorName) throws MalformedURLException {
        String platform = System.getProperty("platform", "web");
        EnvironmentVariables environmentVariables= SystemEnvironmentVariables.createEnvironmentVariables();
        String hub= EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("myconfig"+platform+".devices."+ actorName+".appium.hub");
        String udid=EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("myconfig"+platform+".devices."+ actorName +".appium.udid");
        String platformName=EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("myconfig"+platform+".devices."+ actorName +".appium.platformName");
        String automationName=EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("myconfig"+platform+".devices."+ actorName+".appium.automationName");
        String deviceName=EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("myconfig"+platform+".devices."+ actorName +".appium.deviceName");
        String platformVersion=EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("myconfig"+platform+".devices."+ actorName +".appium.platformVersion");
        String app=EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("myconfig"+platform+".devices."+ actorName +".appium.app");

        DesiredCapabilities caps= new DesiredCapabilities();
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("udid", udid);
        caps.setCapability("automationName",automationName);
        caps.setCapability("platformName",platformName);
        caps.setCapability("platformVersion",platformVersion);
        caps.setCapability("app",app);
        // Lấy tên nền tảng (Android/iOS)
        // Tạo driver cho từng nền tảng
        if ("Android".equalsIgnoreCase(platformName)) return new AndroidDriver(new URL(hub), caps);
        return new IOSDriver(new URL(hub), caps);



    }
}
