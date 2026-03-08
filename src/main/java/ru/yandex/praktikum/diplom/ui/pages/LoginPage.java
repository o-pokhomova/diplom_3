package ru.yandex.praktikum.diplom.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.diplom.ui.Urls;

public class LoginPage extends BasePage {
    private final By EMAIL_FIELD = textInputByLabel("Email");
    private final By PASSWORD_FIELD = textInputByLabel("Пароль");
    private final By REGISTRATION_LINK = By.xpath("//a[text() = \"Зарегистрироваться\"]");
    private final By RESTORE_PASSWORD_LINK = By.xpath("//a[text() = \"Восстановить пароль\"]");
    private final By LOGIN_BTN = By.xpath("//button[text() = \"Войти\"]");


    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickRegistration() {
        waitAndClick(REGISTRATION_LINK);
    }

    public void clickLogin() {
        waitAndClick(LOGIN_BTN);
    }

    public void clickRestorePassword() {
        waitAndClick(RESTORE_PASSWORD_LINK);
    }

    public void awaitForOpened() {
        waitForUrl(Urls.LOGIN_PAGE_URL);
    }

    public void fillEmail(String email) {
        fillField(EMAIL_FIELD, email);
    }

    public void fillPassword(String password) {
        fillField(PASSWORD_FIELD, password);
    }
}
