package ru.yandex.praktikum.diplom;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.diplom.ui.Browser;
import ru.yandex.praktikum.diplom.ui.BrowserStarter;
import ru.yandex.praktikum.diplom.ui.pages.ForgotPasswordPage;
import ru.yandex.praktikum.diplom.ui.pages.LoginPage;
import ru.yandex.praktikum.diplom.ui.pages.MainPage;
import ru.yandex.praktikum.diplom.ui.pages.RegistrationPage;

import java.time.Duration;

public abstract class BaseTest {
    protected final Browser browser;
    protected final WebDriver webDriver;
    protected final WebDriverWait wait;
    protected final MainPage mainPage;
    protected final LoginPage loginPage;
    protected final RegistrationPage registrationPage;
    protected final ForgotPasswordPage forgotPasswordPage;

    public BaseTest() {
        String browserName = System.getProperty("browser");
        if (browserName == null) {
            this.browser = Browser.YANDEX;
        } else {
            this.browser = Browser.valueOf(browserName.toUpperCase());
        }

        webDriver = BrowserStarter.start(this.browser);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        mainPage = new MainPage(webDriver, wait);
        loginPage = new LoginPage(webDriver, wait);
        registrationPage = new RegistrationPage(webDriver, wait);
        forgotPasswordPage = new ForgotPasswordPage(webDriver, wait);
    }

    @After
    public final void closeBrowser() {
        webDriver.quit();
    }
}
