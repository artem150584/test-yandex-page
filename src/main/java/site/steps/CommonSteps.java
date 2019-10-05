package site.steps;

import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.ScenarioContext;

import static org.junit.Assert.assertTrue;

public class CommonSteps {
    private WebDriver driver;

    public CommonSteps(ScenarioContext scenarioContext) {
        this.driver = scenarioContext.getDriver();
    }

    @Then("^should be opened (.*) page$")
    public void checkUrl(String url) {
        WebDriverWait wait5s = new WebDriverWait(driver, 10);
        assertTrue(String.format("%s is not opened", url), wait5s.until(ExpectedConditions.urlContains(url)));
    }
}
