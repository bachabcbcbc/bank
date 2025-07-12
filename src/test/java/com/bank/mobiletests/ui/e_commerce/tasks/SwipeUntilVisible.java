package com.bank.mobiletests.ui.e_commerce.tasks;

import com.bank.mobiletests.platform.AppiumObject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.*;
import net.serenitybdd.screenplay.Task;
import java.util.logging.Logger;

public class SwipeUntilVisible extends AppiumObject implements Task {
    private static final Logger LOGGER = Logger.getLogger(SwipeUntilVisible.class.getName());

    private final Target target;
    private final Direction direction;
    private final int maxSwipes;

    public SwipeUntilVisible(Target target, Direction direction, int maxSwipes) {
        this.target = target;
        this.direction = direction;
        this.maxSwipes = maxSwipes;
    }

    public static SwipeUntilVisible to(Target target, int maxSwipes, Direction direction) {
        return new SwipeUntilVisible(target, direction, maxSwipes);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        LOGGER.info("SwipeUntilVisible started");
        WebDriver driver = getDriver(actor);

        int swipes = 0;
        try {
            By locator = getLocator(target, driver);
            while (!isVisibleNow(driver, locator) && swipes < maxSwipes) {
                if (direction == Direction.UP) {
                    swipeUp(actor);
                } else if (direction == Direction.DOWN) {
                    swipeDown(actor);
                }
                swipes++;
            }
            if (!isVisibleNow(driver, locator)) {
                LOGGER.info("SwipeUntilVisible failed");
                throw new AssertionError("Element not found after swiping: " + target.getName());
            }
        } finally {
            LOGGER.info("SwipeUntilVisible completed");
        }
    }

    public enum Direction {
        UP,
        DOWN
    }
}
