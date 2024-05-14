package org.example.pages;

import org.apache.log4j.Logger;
import org.example.webDriver.Browser;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected static final Logger logger = Logger.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = Browser.getDriver();
    }

    public boolean isDisplayed() {
        return false;
    }

    public void openPage(String url) {
        driver.get(url);
        /* todo:
        не совсем удачное решение на мой взгля,
        так как у метода public void openPage(String url)
        нет приемущества перед driver.get(url);
        (например в плане уменьшение строк кода)
на занятии мы рассматривали пример public void openPage()
в этом случае мы открывали конкретную url, хранящуюся в pageObject
         */
    }

    protected void refresh() {
        driver.navigate().refresh();
    }
}
      /*todo:
      от препода:
      можно класс сделать абстрактным и реализоввывать соответствующий метод
       только для конкретной страницы

      Предлагаю пересмотреть декомпозицию BasePage->PAGES
сделать base page и некоторые методы абстрактными абстрактной.
Например мы ведь не можем открыть BasePage, мы всегда открываем определенную страницу.
Частично это сделано, но нужно доделать
Добавить ожидания для улучшения стабильности
для ключевых элементов(сообщения о ошибке, индиктор успешного логина)
пересмотреть структуру и доступные методы у страниц,
в зависиости от доступной функциональности
       */
