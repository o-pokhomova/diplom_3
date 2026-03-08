package ru.yandex.praktikum.diplom.ui.steps;

import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import ru.yandex.praktikum.diplom.ui.pages.LoginPage;
import ru.yandex.praktikum.diplom.ui.pages.MainPage;
import ru.yandex.praktikum.diplom.ui.pages.RegistrationPage;

@RequiredArgsConstructor
public class RegistrationSteps {
    private final MainPage mainPage;
    private final LoginPage loginPage;
    private final RegistrationPage registrationPage;

    @Step("Заполнить и отправить форму регистрации")
    public void submitRegistrationForm(String email, String password, String name) {
        registrationPage.fillEmailField(email);
        registrationPage.fillPasswordField(password);
        registrationPage.fillNameField(name);
        registrationPage.clickRegister();
    }

    @Step("Дождаться отправки формы регистрации и открытия главной страницы")
    public void awaitForRegistrationFormSumbitted() {
        loginPage.awaitForOpened();
    }

    @Step("Перейти на форму регистрации")
    public void navigateToRegistrationForm() {
        mainPage.clickAccount();
        loginPage.awaitForOpened();
        loginPage.clickRegistration();
        registrationPage.awaitForOpened();
    }

    @Step("Дождаться появления сообщения о некорректном пароле")
    public void awaitForRegistrationFormIncorrectPassword() {
        registrationPage.waitForIncorrectPasswordError();
    }
}
