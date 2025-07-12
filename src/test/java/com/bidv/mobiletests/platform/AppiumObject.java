package com.bidv.mobiletests.platform;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bidv.mobiletests.ProviderDriver;

import io.appium.java_client.AppiumDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

/**
 * A base class representing the interactions of AppiumDriver.
 * 
 * @author jacob
 * 
 */
public abstract class AppiumObject extends ProviderDriver<AppiumDriver>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppiumObject.class);

    public void swipeDown(Actor actor) {
    	Dimension size = getDriver(actor).manage().window().getSize();

        int startY = (int) (size.getHeight() * 0.50);
        int endY = (int) (size.getHeight() * 0.20);
        int startX = size.getWidth() / 2;

        performSwipe(actor, startX, startY, startX, endY);
    }

    public void swipeUp(Actor actor) {
    	Dimension size = getDriver(actor).manage().window().getSize();

        int startY = (int) (size.getHeight() * 0.50);
        int endY = (int) (size.getHeight() * 0.20);
        int startX = size.getWidth() / 2;

        performSwipe(actor, startX, endY, startX, startY);
    }

    public void swipeLeft(Actor actor) {
    	Dimension size = getDriver(actor).manage().window().getSize();

        int startY = (int) (size.getHeight() / 2.0);
        int startX = (int) (size.getWidth() * 0.90);
        int endX = (int) (size.getWidth() * 0.05);

        performSwipe(actor, startX, startY, endX, startY);
    }

    public void swipeRight(Actor actor) {
    	Dimension size = getDriver(actor).manage().window().getSize();
    	
        int startY = (int) (size.getHeight() / 2.0);
        int startX = (int) (size.getWidth() * 0.05);
        int endX = (int) (size.getWidth() * 0.90);

        performSwipe(actor, startX, startY, endX, startY);
    }

    private void performSwipe(Actor actor, int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        getDriver(actor).perform(Arrays.asList(sequence));
    }

    public final static Dimension getScreenSize(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().manage().window().getSize();
    }
    
    public void back(Actor actor) {
        getDriver(actor).navigate().back();
    }
}
