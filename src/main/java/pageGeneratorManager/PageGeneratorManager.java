package pageGeneratorManager;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageGeneratorManager {
    //2nd way to create instance of page object
    //Use: HomePage homePage = PageGeneratorManager.getPage(HomePage.class, driver);
    //Use for navigation methods that return different page objects <Manage the navigation to other page>
    public static <T> T getPage(Class<T> pageClass, WebDriver driver) {
        try {
            Constructor<T> constructor = pageClass.getDeclaredConstructor(WebDriver.class);
            return constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Cannot init page object class: " + pageClass.getName(), e);
        }
    }

}
