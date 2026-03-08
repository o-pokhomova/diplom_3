Запуск тестов командой
```
mvn -Dbrowser=$BROWSER clean install allure:report
```

Переменная browser определяет, в каком браузере запустятся тесты
Возможные значения:
 * chrome
 * yandex
Если переменная не задана, используется Яндекс.Браузер 

Путь к драйверу Yandex-браузера в классе BrowserStarter