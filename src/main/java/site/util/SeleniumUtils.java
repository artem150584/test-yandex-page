package site.util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumUtils {
    public static WebElement byXpath(WebDriver driver, String xPath) {
        try {
            return driver.findElement(By.xpath(xPath));
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
