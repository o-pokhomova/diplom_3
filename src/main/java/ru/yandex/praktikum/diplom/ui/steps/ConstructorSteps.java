package ru.yandex.praktikum.diplom.ui.steps;

import lombok.RequiredArgsConstructor;
import ru.yandex.praktikum.diplom.ui.pages.MainPage;

@RequiredArgsConstructor
public class ConstructorSteps {
    private final MainPage mainPage;

    public void clickTab(String tab) {
        mainPage.clickTab(tab);
    }

    public boolean checkTabClicked(String tabHeader) {
        return mainPage.checkSingleTabSelected(tabHeader);
    }
}
