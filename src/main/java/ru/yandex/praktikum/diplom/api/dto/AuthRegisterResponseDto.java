package ru.yandex.praktikum.diplom.api.dto;

import lombok.Data;

@Data
public class AuthRegisterResponseDto {
    private Boolean success;
    private User user;
    private String accessToken;
    private String refreshToken;
}
