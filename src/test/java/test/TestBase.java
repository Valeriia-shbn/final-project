package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void setupAllureSelenide() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true) // Include screenshots in the report
                .savePageSource(true)); // Include page source in the report
    }
}
