package com.bank.mobiletests.platform;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bank.mobiletests.ProviderDriver;

import io.appium.java_client.AppiumDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

/**
 * A base class representing the interactions of AppiumDriver.
 * 
 * @author jacob
 * 
 */
public abstract class AppiumObject extends ProviderDriver<AppiumDriver>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppiumObject.class);

    public void swipeDown(Actor actor) {
        LOGGER.info("{} is performing swipe down gesture", actor.getName());
    	Dimension size = getDriver(actor).manage().window().getSize();

        int startY = (int) (size.getHeight() * 0.50);
        int endY = (int) (size.getHeight() * 0.20);
        int startX = size.getWidth() / 2;

        LOGGER.debug("Swipe down coordinates: startX={}, startY={}, endX={}, endY={}", startX, startY, startX, endY);
        performSwipe(actor, startX, startY, startX, endY);
        LOGGER.info("Swipe down gesture completed successfully");
    }

    public void swipeUp(Actor actor) {
        LOGGER.info("{} is performing swipe up gesture", actor.getName());
    	Dimension size = getDriver(actor).manage().window().getSize();

        int startY = (int) (size.getHeight() * 0.50);
        int endY = (int) (size.getHeight() * 0.20);
        int startX = size.getWidth() / 2;

        LOGGER.debug("Swipe up coordinates: startX={}, startY={}, endX={}, endY={}", startX, endY, startX, startY);
        performSwipe(actor, startX, endY, startX, startY);
        LOGGER.info("Swipe up gesture completed successfully");
    }

    public void swipeLeft(Actor actor) {
        LOGGER.info("{} is performing swipe left gesture", actor.getName());
    	Dimension size = getDriver(actor).manage().window().getSize();

        int startY = (int) (size.getHeight() / 2.0);
        int startX = (int) (size.getWidth() * 0.90);
        int endX = (int) (size.getWidth() * 0.05);

        LOGGER.debug("Swipe left coordinates: startX={}, startY={}, endX={}, endY={}", startX, startY, endX, startY);
        performSwipe(actor, startX, startY, endX, startY);
        LOGGER.info("Swipe left gesture completed successfully");
    }

    public void swipeRight(Actor actor) {
        LOGGER.info("{} is performing swipe right gesture", actor.getName());
    	Dimension size = getDriver(actor).manage().window().getSize();
    	
        int startY = (int) (size.getHeight() / 2.0);
        int startX = (int) (size.getWidth() * 0.05);
        int endX = (int) (size.getWidth() * 0.90);

        LOGGER.debug("Swipe right coordinates: startX={}, startY={}, endX={}, endY={}", startX, startY, endX, startY);
        performSwipe(actor, startX, startY, endX, startY);
        LOGGER.info("Swipe right gesture completed successfully");
    }

    private void performSwipe(Actor actor, int startX, int startY, int endX, int endY) {
        LOGGER.debug("Executing swipe gesture for {}: ({},{}) to ({},{})", actor.getName(), startX, startY, endX, endY);
        
        try {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence sequence = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            
            getDriver(actor).perform(Arrays.asList(sequence));
            LOGGER.debug("Swipe gesture executed successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to execute swipe gesture: {}", e.getMessage(), e);
            throw e;
        }
    }

    public final static Dimension getScreenSize(Actor actor) {
        Dimension size = BrowseTheWeb.as(actor).getDriver().manage().window().getSize();
        LOGGER.debug("Screen size for {}: {}x{}", actor.getName(), size.getWidth(), size.getHeight());
        return size;
    }
    
    public void back(Actor actor) {
        LOGGER.info("{} is navigating back", actor.getName());
        try {
            getDriver(actor).navigate().back();
            LOGGER.info("Navigation back completed successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to navigate back: {}", e.getMessage(), e);
            throw e;
        }
    }

    public boolean isVisibleNow(WebDriver driver, By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            if (elements.isEmpty()) return false;
    
            WebElement element = elements.get(0);
    
            if (driver.getClass().getName().toLowerCase().contains("android")) {
                LOGGER.info("android {} isVisibleNow");
                // Android-specific workaround
                // Some elements still return false for isDisplayed() even when visible,
                // so check bounds or visibility attribute if available
                String visibleAttr = element.getAttribute("visible");
                String displayedAttr = element.getAttribute("displayed");
                // String bounds = element.getAttribute("bounds");
    
                return ("true".equalsIgnoreCase(visibleAttr)
                        || "true".equalsIgnoreCase(displayedAttr));
                    //    && bounds != null && !bounds.equals("[0,0][0,0]");
            } else {
                LOGGER.info("ios {} isVisibleNow");
                // iOS or default Web: rely on standard isDisplayed()
                return element.isDisplayed();
            }
    
        } catch (Exception e) {
            return false;
        }
    }
    

    public By getLocator(Target target, WebDriver driver) {
        try {
            java.lang.reflect.Field field;
            if (driver.getClass().getName().toLowerCase().contains("android")) {
                field = target.getClass().getDeclaredField("androidLocator");
            } else {
                field = target.getClass().getDeclaredField("iosLocator");
            }
            field.setAccessible(true);
            return (By) field.get(target);
        } catch (Exception e) {
            throw new RuntimeException("Could not extract locator from ByTarget", e);
        }
    }

    
}
