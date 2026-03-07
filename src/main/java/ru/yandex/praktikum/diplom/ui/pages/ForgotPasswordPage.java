package ru.yandex.praktikum.diplom.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.diplom.ui.Urls;

public class ForgotPasswordPage extends BasePage {
    private final By LOGIN_LINK = By.xpath("//a[text() = \"Войти\"]");

    public ForgotPasswordPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void awaitForOpened() {
        waitForUrl(Urls.FORGOT_PASSWORD);
    }

    public void clickLogin() {
        waitAndClick(LOGIN_LINK);
    }
}
