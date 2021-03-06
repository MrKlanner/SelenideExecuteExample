package com.github.kskabort;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import java.text.MessageFormat;

import static com.codeborne.selenide.Selenide.*;
import static com.github.kskabort.Driver.*;

public class SimpleTest extends A_BaseTest {

    @Test
    public void openPage() {
        open("https://google.com");
        $(".gLFyf.gsfi").val("тест").pressEnter();
        ElementsCollection link = $$(".yuRUbf");
        System.out.println(MessageFormat.format(
            "Number of \"Link\" elements is {0}:\n{1}",
            link.size(),
            link
        ));
        final int index = 3;
        SelenideElement link1 = $$(".rc").get(index).execute($(link.first()));
        System.out.println(MessageFormat.format(
            "\nSearch element \"Link\" in {0}rd element:\n{1}",
            index,
            link1
        ));
        link1.execute(openInNewTab);
        sleep(5000L);
    }
}