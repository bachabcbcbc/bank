package com.bidv.mobiletests.platform.ios.interactions;

import com.bidv.mobiletests.platform.ios.IOSObject;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class AlertAceept extends IOSObject implements Interaction {

	@Override
	public <T extends Actor> void performAs(T t) {
		getDriver(t).switchTo().alert().accept();
	}

}