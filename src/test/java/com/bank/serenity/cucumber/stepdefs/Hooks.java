package com.bank.serenity.cucumber.stepdefs;
import io.cucumber.java.Before;

import org.openqa.selenium.WebDriver;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.annotations.Managed;


import java.net.MalformedURLException;

public class Hooks {
    @Managed(driver = "Appium")
    public WebDriver mobileDevice;
    @Before
    public void set_the_stage() throws MalformedURLException {
        OnStage.setTheStage(new DualDeviceCast());
    }
}