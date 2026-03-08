package ru.yandex.praktikum.diplom.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserStarter {
    public static final String WEBDRIVER_CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    public static final String PATH_TO_YANDEX_DRIVER = "/Users/opokhomova/yandexdriver";

    public static WebDriver start(Browser browser) {
        System.clearProperty(WEBDRIVER_CHROME_DRIVER_PROPERTY);
        final WebDriver driver;
        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                WebDriverManager.chromedriver().setup();
                break;
            case YANDEX:
                driver = initYandexDriver();
                break;
            default:
                throw new IllegalStateException("Неизвестный браузер");
        }
        return driver;
    }

    @SneakyThrows
    private static WebDriver initYandexDriver() {
        System.setProperty(WEBDRIVER_CHROME_DRIVER_PROPERTY, PATH_TO_YANDEX_DRIVER);
        return new ChromeDriver();
    }
}
