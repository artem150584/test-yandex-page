package site;

import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import site.beans.WebDriverFactory;
import site.config.ConfigIT;

@ContextConfiguration(classes = ConfigIT.class, initializers = ConfigFileApplicationContextInitializer.class)
public class HooksIT {

    private static class CustomThread extends Thread {
        private final WebDriver driver;
        private CustomThread(WebDriver driver) {
            this.driver = driver;
        }
    }

    @Autowired
    private ScenarioContext scenarioContext;

    @Autowired
    private WebDriverFactory webDriverFactory;

    @Before
    public void prepareData() throws IllegalAccessException {
        if (!scenarioContext.isShutdownHookRegistred()) {
            final WebDriver driver = webDriverFactory.getWebDriver();
            scenarioContext.registerShutdownHook(new CustomThread(driver) {
                @Override
                public void run() {
                    driver.quit();
                }
            });

            scenarioContext.setDriver(driver);
        }

        scenarioContext.getDriver().manage().deleteAllCookies();
    }
}
