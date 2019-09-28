package site.steps;

import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class CommonSteps {
    @Autowired
    private WebDriver driver;

    @Then("^should be opened (.*) page$")
    public void checkUrl(String url) {
        WebDriverWait wait5s = new WebDriverWait(driver, 5);
        assertTrue(String.format("%s is not opened", url), wait5s.until(ExpectedConditions.urlContains(url)));
    }
}
