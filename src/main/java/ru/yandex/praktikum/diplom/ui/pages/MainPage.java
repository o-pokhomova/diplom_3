package ru.yandex.praktikum.diplom.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.diplom.ui.Urls;

import java.util.List;

public class MainPage extends BasePage {
    private static final By ACCOUNT_BTN = By.xpath("//a[.//p[text() = \"Личный Кабинет\"]]");
    private static final By LOGIN_BTN = By.xpath("//button[text() = \"Войти в аккаунт\"]");
    private static final String TAB_TEMPLATE = "//span[text() = \"%s\"]";
    private static final By SELECTED_TAB = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openPage() {
        driver.get(Urls.BASE_URL);
        // Добавим ожидание, потому что при загрузке страницы Modal_modal_overlay__x2ZCr перекрывает ссылки
        wainNSeconds(1);
    }

    public void clickAccount() {
        waitAndClick(ACCOUNT_BTN);
    }

    public void clickLogin() {
        waitAndClick(LOGIN_BTN);
    }

    public void awaitForOpened() {
        waitForUrl(Urls.BASE_URL_ENDS_WITH_SLASH);
    }

    public By getLOGIN_BTN() {
        return LOGIN_BTN;
    }

    public void clickTab(String tabHeader) {
        waitAndClick(getTabSelector(tabHeader));
        wainNSeconds(2);
    }

    public boolean checkSingleTabSelected(String tabHeader) {
        List<WebElement> selectedTabs = driver.findElements(SELECTED_TAB);
        if (selectedTabs.size() > 1) {
            System.out.println(selectedTabs);
            return false;
        }
        if (selectedTabs.size() == 0) {
            System.out.println("No tab selected");
            return false;
        }
        selectedTabs.get(0).findElement(getTabSelector(tabHeader));
        return true;
    }

    private By getTabSelector(String tabHeader) {
        return By.xpath(String.format(TAB_TEMPLATE, tabHeader));
    }
}
