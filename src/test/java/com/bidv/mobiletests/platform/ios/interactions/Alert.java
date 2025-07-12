package com.bidv.mobiletests.platform.ios.interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * 
 * @author jacob
 *
 */
public class Alert{

	public void accept() {
		instrumented(AlertAceept.class);
	}

}
