package ru.yandex.praktikum.diplom.ui.steps;

import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import ru.yandex.praktikum.diplom.ui.pages.ForgotPasswordPage;
import ru.yandex.praktikum.diplom.ui.pages.LoginPage;
import ru.yandex.praktikum.diplom.ui.pages.MainPage;
import ru.yandex.praktikum.diplom.ui.pages.RegistrationPage;

@RequiredArgsConstructor
public class LoginSteps {
    public static final String ACCESS_TOKEN = "accessToken";
    private final MainPage mainPage;
    private final LoginPage loginPage;
    private final RegistrationPage registrationPage;
    private final ForgotPasswordPage forgotPasswordPage;

    @Step("Перейти на форму логина через кнопку на главной странице")
    public void navigateToLoginFormFromMain() {
        mainPage.clickLogin();
        loginPage.awaitForOpened();
    }

    @Step("Перейти на форму логина через ссылку на личный кабинет")
    public void navigateToLoginFormFromAccount() {
        mainPage.clickAccount();
        loginPage.awaitForOpened();
    }

    @Step("Перейти на форму логина через форму регистрации")
    public void navigateToLoginFormFromRegistration() {
        mainPage.clickAccount();
        loginPage.awaitForOpened();
        loginPage.clickRegistration();
        registrationPage.awaitForOpened();
        registrationPage.clickLoginLink();
        loginPage.awaitForOpened();
    }

    @Step("Перейти на форму логина через форму восстановления пароля")
    public void navigateToLoginFormFromPasswordRestore() {
        mainPage.clickLogin();
        loginPage.awaitForOpened();
        loginPage.clickRestorePassword();
        forgotPasswordPage.awaitForOpened();
        forgotPasswordPage.clickLogin();
        loginPage.awaitForOpened();
    }

    @Step("Заполнить и отправить формку логина")
    public void submitLoginForm(String email, String password) {
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
        loginPage.clickLogin();
    }

    @Step("Подождать, пока сабмит на форме логина отработает и откроется главная страница")
    public void awaitForLoggedIn() {
        mainPage.awaitForOpened();
    }

    @Step("Получить сохранённый access-token")
    public String getAccessToken() {
        return mainPage.getKeyFromLocalStorage(ACCESS_TOKEN);
    }
}
