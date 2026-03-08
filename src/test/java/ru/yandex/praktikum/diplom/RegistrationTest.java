package ru.yandex.praktikum.diplom;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import ru.yandex.praktikum.diplom.api.dto.AuthRegisterResponseDto;
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

    @After
    public void tearDown() {
        Response response = userSteps.login(email, password);
        if (response.statusCode() == HttpServletResponse.SC_OK) {
            String accessToken = response
                    .body().as(AuthRegisterResponseDto.class)
                    .getAccessToken();
            Response deleteResponse = userSteps.delete(accessToken);
            if (deleteResponse.statusCode() != HttpServletResponse.SC_ACCEPTED) {
                System.out.println("Не удалось удалить пользователя");
            }
        } else if (response.statusCode() == HttpServletResponse.SC_UNAUTHORIZED) {
            System.out.println("Пользователя не существует");
        } else {
            System.out.println("Неожиданный статус-код " + response.statusCode() + " при получении токена.");
        }
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
