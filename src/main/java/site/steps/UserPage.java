package site.steps;

import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UserPage {
    @Autowired
    private WebDriver driver;

    @Then("^should be shown the following user's data::$")
    public void checkProfilePage(Map<String, String> params) {
        Map<String, String> attrs = new HashMap<>();
        attrs.put("first name", "personal-info__first");
        attrs.put("last name", "personal-info__last");
        attrs.put("login", "personal-info-login personal-info-login__displaylogin");

        for (Map.Entry<String, String> param : params.entrySet()) {
            String xPath = String.format("//*[@class=\"%s\"]", attrs.get(param.getKey()));
            assertEquals(String.format("Wrong %s", param.getKey()),
                    param.getValue(),
                    driver.findElement(By.xpath(xPath)).getText());
        }
    }
}
