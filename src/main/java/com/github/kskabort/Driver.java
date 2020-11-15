package com.github.kskabort;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.switchTo;

public class Driver {

    public static WebDriver currentDriver() {
        return WebDriverRunner.getWebDriver();
    }

    public static Command<SelenideElement> $(SelenideElement el) {
        return (proxy, locator, args) -> {
            if (el == null) {
                throw new IllegalArgumentException("Missing target argument");
            } else {
                String classes = el.attr("class");
                if (classes != null && !classes.equals("")) {
                    if (classes.contains(" ")) {
                        classes = classes.replace(" ", ".");
                    }
                    classes = ".".concat(classes);
                    return proxy.$(classes);
                } else {
                    throw new ElementNotFound(locator.driver(), By.cssSelector("body"), Condition.exist);
                }
            }
        };
    }

    public static Command<SelenideElement> openInNewTab = new Command<>() {
        @Nonnull
        @Override
        @ParametersAreNonnullByDefault
        public SelenideElement execute(SelenideElement proxy, WebElementSource locator, @javax.annotation.Nullable Object[] args) {
            Keys key = Keys.CONTROL;
            if (System.getProperty("os.name").contains("Mac OS")) {
                key = Keys.META;
            }
            actions()
                .keyDown(key)
                .click(proxy)
                .keyUp(key)
                .perform();
            Assert.assertEquals(
                currentDriver().getWindowHandles().size(),
                2,
                "Wrong num of tabs!"
            );
            var webDriver = switchTo().window(1);
            return proxy;
        }
    };
}