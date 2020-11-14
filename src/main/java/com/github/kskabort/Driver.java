package com.github.kskabort;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;

public class Driver {

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
                    throw new ElementNotFound(locator.driver(), By.className(" "), Condition.exist);
                }
            }
        };
    }
}