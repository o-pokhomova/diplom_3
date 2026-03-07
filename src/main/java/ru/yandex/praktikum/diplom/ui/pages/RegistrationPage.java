package ru.yandex.praktikum.diplom.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.diplom.ui.Urls;

public class RegistrationPage extends BasePage {
    private final By EMAIL_FIELD = textInputByLabel("Email");
    private final By NAME_FIELD = textInputByLabel("Имя");
    private final By PASSWORD_FIELD = textInputByLabel("Пароль");
    private final By REGISTER_BTN = By.xpath("//button[text() = \"Зарегистрироваться\"]");
    private final By INCORRECT_PASSWORD_P = By.xpath("//p[contains(@class, 'input__error') and text() = \"Некорректный пароль\"]");
    private final By LOGIN_LINK = By.xpath("//a[text() = \"Войти\"]");

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void fillEmailField(String email) {
        fillField(EMAIL_FIELD, email);
    }

    public void fillPasswordField(String password) {
        fillField(PASSWORD_FIELD, password);
    }

    public void fillNameField(String name) {
        fillField(NAME_FIELD, name);
    }

    public void clickRegister() {
        waitAndClick(REGISTER_BTN);
    }

    public void waitForIncorrectPasswordError() {
        waitForVisibility(INCORRECT_PASSWORD_P);
    }

    public void awaitForOpened() {
        waitForUrl(Urls.REGISTRATION_PAGE_URL);
    }

    public void clickLoginLink() {
        waitAndClick(LOGIN_LINK);
    }
}
