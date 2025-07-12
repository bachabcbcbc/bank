package com.bidv.serenity.cucumber.stepdefs;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.annotations.Managed;

public class Hooks {
    @Managed(driver = "Appium")
    public WebDriver mobileDevice;

    @Before
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
    }
}