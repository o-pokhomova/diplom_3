package ru.yandex.praktikum.diplom;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.yandex.praktikum.diplom.ui.Browser;
import ru.yandex.praktikum.diplom.ui.steps.RegistrationSteps;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Регистрация пользователя")
public class RegistrationTest extends BaseUserTest {
    private final RegistrationSteps registrationSteps = new RegistrationSteps(
            mainPage,
            loginPage,
            registrationPage
    );

    public RegistrationTest(Browser browser) {
        super(browser);
    }

    @After
    public void tearDown() {
        userSteps.delete(email, password);
    }

    @DisplayName("Успешная регистрация пользователя")
    @Description("Зарегистрировать пользователя в системе")
    @Test
    public void register() {
        mainPage.openPage();
        registrationSteps.navigateToRegistrationForm();

        registrationSteps.submitRegistrationForm(email, password, name);

        registrationSteps.awaitForRegistrationFormSumbitted();
        userSteps.login(email, password)
                .then()
                .statusCode(HttpServletResponse.SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name));
    }

    @DisplayName("Неудачная регистрация пользователя: короткий пароль")
    @Description("Не удалось зарегистрировать пользователя в системе, пароль должен быть не короче 6 символов")
    @Test
    public void registerIncorrectPassword() {
        final String shortPassword = password.substring(0, 5);

        mainPage.openPage();
        registrationSteps.navigateToRegistrationForm();

        registrationSteps.submitRegistrationForm(email, shortPassword, name);

        registrationSteps.awaitForRegistrationFormIncorrectPassword();
        userSteps.login(email, shortPassword)
                .then()
                .statusCode(HttpServletResponse.SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}
