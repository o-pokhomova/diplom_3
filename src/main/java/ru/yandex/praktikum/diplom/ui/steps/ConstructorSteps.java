package ru.yandex.praktikum.diplom.ui.steps;

import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import ru.yandex.praktikum.diplom.ui.pages.MainPage;

@RequiredArgsConstructor
public class ConstructorSteps {
    private final MainPage mainPage;

    @Step("Клик на вкладке конструктора")
    public void clickTab(String tab) {
        mainPage.clickTab(tab);
    }

    @Step("Узнать, является ли вкладка конструктора активной")
    public boolean isTabActive(String tabHeader) {
        return mainPage.isTabActive(tabHeader);
    }
}
