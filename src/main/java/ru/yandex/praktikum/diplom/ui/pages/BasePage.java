package ru.yandex.praktikum.diplom.ui.pages;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RequiredArgsConstructor
public class BasePage {
    public static final String TEXT_INPUT_WITH_LABEL_TEMPLATE = "//label[text() = \"%s\"]/following-sibling::input[1]";
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected void waitAndClick(By selector) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(selector))).click();
    }

    protected void waitForUrl(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    protected WebElement waitForVisibility(By selector) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    protected void fillField(By selector, String value) {
        waitForVisibility(selector);
        driver.findElement(selector).sendKeys(value);
    }

    protected By textInputByLabel(String label) {
        return By.xpath(String.format(TEXT_INPUT_WITH_LABEL_TEMPLATE, label));
    }

    public String getKeyFromLocalStorage(String key) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) driver);
        RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
        return webStorage.getLocalStorage().getItem("accessToken");
    }
}
