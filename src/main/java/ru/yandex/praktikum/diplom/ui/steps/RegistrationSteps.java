package ru.yandex.praktikum.diplom.ui.steps;

import lombok.RequiredArgsConstructor;
import ru.yandex.praktikum.diplom.ui.pages.LoginPage;
import ru.yandex.praktikum.diplom.ui.pages.MainPage;
import ru.yandex.praktikum.diplom.ui.pages.RegistrationPage;

@RequiredArgsConstructor
public class RegistrationSteps {
    private final MainPage mainPage;
    private final LoginPage loginPage;
    private final RegistrationPage registrationPage;

    public void submitRegistrationForm(String email, String password, String name) {
        registrationPage.fillEmailField(email);
        registrationPage.fillPasswordField(password);
        registrationPage.fillNameField(name);
        registrationPage.clickRegister();
    }

    public void awaitForRegistrationFormSumbitted() {
        loginPage.awaitForOpened();
    }

    public void navigateToRegistrationForm() {
        mainPage.clickAccount();
        loginPage.awaitForOpened();
        loginPage.clickRegistration();
        registrationPage.awaitForOpened();
    }

    public void awaitForRegistrationFormIncorrectPassword() {
        registrationPage.waitForIncorrectPasswordError();
    }
}
