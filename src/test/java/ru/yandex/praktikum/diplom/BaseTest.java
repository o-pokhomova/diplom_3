package ru.yandex.praktikum.diplom;

import io.qameta.allure.Allure;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.diplom.ui.Browser;
import ru.yandex.praktikum.diplom.ui.BrowserStarter;
import ru.yandex.praktikum.diplom.ui.pages.ForgotPasswordPage;
import ru.yandex.praktikum.diplom.ui.pages.LoginPage;
import ru.yandex.praktikum.diplom.ui.pages.MainPage;
import ru.yandex.praktikum.diplom.ui.pages.RegistrationPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public abstract class BaseTest {
    protected final Browser browser;
    protected final WebDriver webDriver;
    protected final WebDriverWait wait;
    protected final MainPage mainPage;
    protected final LoginPage loginPage;
    protected final RegistrationPage registrationPage;
    protected final ForgotPasswordPage forgotPasswordPage;

    public BaseTest(Browser browser) {
        this.browser = browser;
        webDriver = BrowserStarter.start(this.browser);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        mainPage = new MainPage(webDriver, wait);
        loginPage = new LoginPage(webDriver, wait);
        registrationPage = new RegistrationPage(webDriver, wait);
        forgotPasswordPage = new ForgotPasswordPage(webDriver, wait);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Browser> data() {
        return Arrays.asList(Browser.values());
    }

    @Before
    public final void initTestNameForAllureBrowser() {
        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setName(testResult.getName() + ": " + browser)
        );
    }

    @After
    public final void closeBrowser() {
        webDriver.quit();
    }
}
