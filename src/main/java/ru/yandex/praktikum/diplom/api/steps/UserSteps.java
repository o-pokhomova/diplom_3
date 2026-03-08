package ru.yandex.praktikum.diplom.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.diplom.api.Endpoints;
import ru.yandex.praktikum.diplom.api.dto.AuthLoginRequestDto;
import ru.yandex.praktikum.diplom.api.dto.AuthRegisterRequestDto;
import ru.yandex.praktikum.diplom.api.dto.AuthRegisterResponseDto;

import javax.servlet.http.HttpServletResponse;

public class UserSteps extends BaseSteps {
    @Step("Регистрация")
    public Response register(String email, String password, String name) {
        AuthRegisterRequestDto requestBody = new AuthRegisterRequestDto(
                email,
                password,
                name
        );
        return prepareRestSpec()
                .and().body(requestBody)
                .when()
                .post(Endpoints.AUTH_REGISTER);
    }

    @Step("Вход")
    public Response login(String email, String password) {
        AuthLoginRequestDto requestBody = new AuthLoginRequestDto(
                email,
                password
        );
        return prepareRestSpec()
                .and().body(requestBody)
                .when()
                .post(Endpoints.AUTH_LOGIN);
    }

    @Step("Удаление")
    public Response delete(String token) {
        return prepareRestSpec(token).when().delete(Endpoints.AUTH_USER);
    }
}
