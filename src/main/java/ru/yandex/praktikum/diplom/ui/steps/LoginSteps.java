package ru.yandex.praktikum.diplom.ui.steps;

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

    public void navigateToLoginFormFromMain() {
        mainPage.clickLogin();
        loginPage.awaitForOpened();
    }

    public void navigateToLoginFormFromAccount() {
        mainPage.clickAccount();
        loginPage.awaitForOpened();
    }

    public void navigateToLoginFormFromRegistration() {
        mainPage.clickAccount();
        loginPage.awaitForOpened();
        loginPage.clickRegistration();
        registrationPage.awaitForOpened();
        registrationPage.clickLoginLink();
        loginPage.awaitForOpened();
    }

    public void navigateToLoginFormFromPasswordRestore() {
        mainPage.clickLogin();
        loginPage.awaitForOpened();
        loginPage.clickRestorePassword();
        forgotPasswordPage.awaitForOpened();
        forgotPasswordPage.clickLogin();
        loginPage.awaitForOpened();
    }

    public void submitLoginForm(String email, String password) {
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
        loginPage.clickLogin();
    }

    public void awaitForLoggedIn() {
        mainPage.awaitForOpened();
    }

    public String getAccessToken() {
        return mainPage.getKeyFromLocalStorage(ACCESS_TOKEN);
    }

    public void waitNSeconds(int seconds) {
        loginPage.wainNSeconds(seconds);
    }
}
