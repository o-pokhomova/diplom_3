package ru.yandex.praktikum.diplom;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.diplom.api.dto.AuthRegisterResponseDto;
import ru.yandex.praktikum.diplom.ui.Browser;
import ru.yandex.praktikum.diplom.ui.steps.LoginSteps;

@DisplayName("Логин")
public class LoginTest extends BaseUserTest {
    public static final int SECONDS_TO_WAIT_ON_INCORRECT_LOGIN = 2;
    private final LoginSteps loginSteps = new LoginSteps(mainPage, loginPage, registrationPage, forgotPasswordPage);
    private String accessToken;

    public LoginTest(Browser browser) {
        super(browser);
    }

    @Before
    public void setUp() {
        accessToken = userSteps.register(email, password, name)
                .body()
                .as(AuthRegisterResponseDto.class)
                .getAccessToken();
    }

    @After
    public void tearDown() {
        userSteps.delete(accessToken);
    }

    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    @Test
    public void loginFromMainPage() {
        mainPage.openPage();
        loginSteps.navigateToLoginFormFromMain();

        testSuccessLogin();
    }

    @DisplayName("Неудачный вход по кнопке «Войти в аккаунт» на главной: неправильный пароль")
    @Description("Неудачный вход по кнопке «Войти в аккаунт» на главной: неправильный пароль")
    @Test
    public void loginFromMainPageIncorrectPassword() {
        mainPage.openPage();
        loginSteps.navigateToLoginFormFromMain();

        loginSteps.submitLoginForm(email, "Some bad password");

        loginSteps.waitNSeconds(SECONDS_TO_WAIT_ON_INCORRECT_LOGIN);
        Assert.assertNull(
                "Токен отсутствует в локальном хранилище браузера",
                loginSteps.getAccessToken()
        );
    }

    @DisplayName("Неудачный вход по кнопке «Войти в аккаунт» на главной: неправильный логин")
    @Description("Неудачный вход по кнопке «Войти в аккаунт» на главной: неправильный логин")
    @Test
    public void loginFromMainPageIncorrectLogin() {
        mainPage.openPage();
        loginSteps.navigateToLoginFormFromMain();

        loginSteps.submitLoginForm("Bad login", password);

        loginSteps.waitNSeconds(SECONDS_TO_WAIT_ON_INCORRECT_LOGIN);
        Assert.assertNull(
                "Токен отсутствует в локальном хранилище браузера",
                loginSteps.getAccessToken()
        );
    }

    @DisplayName("Вход по кнопке «Личный кабинет» на главной")
    @Description("Вход по кнопке «Личный кабинет» на главной")
    @Test
    public void loginFromAccountBtn() {
        mainPage.openPage();
        loginSteps.navigateToLoginFormFromAccount();

        testSuccessLogin();
    }

    @DisplayName("Вход по кнопке на странице регистрации")
    @Description("Вход по кнопке на странице регистрации")
    @Test
    public void loginFromRegistrationBtn() {
        mainPage.openPage();
        loginSteps.navigateToLoginFormFromRegistration();

        testSuccessLogin();
    }

    @DisplayName("Вход по кнопке на странице забытого пароля")
    @Description("Вход по кнопке на странице забытого пароля")
    @Test
    public void loginFromPasswordRestore() {
        mainPage.openPage();
        loginSteps.navigateToLoginFormFromPasswordRestore();

        testSuccessLogin();
    }

    private void testSuccessLogin() {
        loginSteps.submitLoginForm(email, password);

        loginSteps.awaitForLoggedIn();
        Assert.assertNotNull(
                "Токен отсутствует в локальном хранилище браузера",
                loginSteps.getAccessToken()
        );
    }
}
