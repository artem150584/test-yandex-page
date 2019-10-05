package site.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;

@Component
public class WebDriverFactory {
    static private final String DRIVER_CHROME = "chrome";
    static private final String DRIVER = "firefox";

    private static final String DRIVER_PROPERTY = "driver";

    public WebDriver getWebDriver() {
        String driverProperty = System.getProperty(DRIVER_PROPERTY);

        final String driverName;
        if (driverProperty != null) {
            driverName = driverProperty.toLowerCase();
        } else {
            driverName = DRIVER;
        }

        final WebDriver driver;
        switch (driverName.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            default:
                driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();

        return driver;
    }
}