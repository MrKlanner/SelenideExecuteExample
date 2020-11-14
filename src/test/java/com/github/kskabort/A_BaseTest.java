package com.github.kskabort;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Browsers.CHROME;

public class A_BaseTest {

    /**
     * Main configuration
     */
    @BeforeSuite(description = "Previous configuration")
    public void beforeSuite() {
        Configuration.browser = CHROME;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setAcceptInsecureCerts(true);
        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
        Configuration.fastSetValue = false;
        Configuration.baseUrl = "https://google.com";
    }
}
