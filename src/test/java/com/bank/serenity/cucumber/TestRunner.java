package com.bank.serenity.cucumber;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = {
        "src/test/resources/features/"
    },
    glue = {
        "com.bank.serenity.cucumber.stepdefs"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"
    },
    tags = "@shopping"
)
public class TestRunner {
} 