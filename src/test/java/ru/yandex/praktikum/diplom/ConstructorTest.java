package ru.yandex.praktikum.diplom;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.diplom.ui.steps.ConstructorSteps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DisplayName("Конструктор бургеров")
@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {
    private final String preparatoryTabHeader;
    private final String mainTabHeader;
    private final ConstructorSteps constructorSteps = new ConstructorSteps(mainPage);

    // Кликаем в одном месте несколько вкладок, потому что сходу нельзя кликнуть
    // по булкам, потому что они и так активная вкладка. Поэтому чтобы протестировать
    // булки, надо сначала кликнуть что-то отличное от них. Такую стратегию распространим
    // и на остальные вкладки
    public ConstructorTest(String preparatoryTabHeader, String mainTabHeader) {
        this.preparatoryTabHeader = preparatoryTabHeader;
        this.mainTabHeader = mainTabHeader;
    }


    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> testData() {
        List<Object[]> result = new ArrayList<>();
        result.add(new Object[]{"Соусы", "Булки"});
        result.add(new Object[]{"Начинки", "Соусы"});
        result.add(new Object[]{"Соусы", "Начинки"});
        return result;
    }

    @Before
    public final void initTestNameForAllureWithBrowserAndTabName() {
        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setName(testResult.getName() + ": " + mainTabHeader)
        );
    }

    @DisplayName("Переключение табов")
    @Description("Проверяем, что работают переходы между разделами")
    @Test
    public void testSwitchToTab() {
        mainPage.openPage();
        constructorSteps.clickTab(preparatoryTabHeader);

        constructorSteps.clickTab(mainTabHeader);

        Assert.assertTrue(constructorSteps.isTabActive(mainTabHeader));
    }
}
