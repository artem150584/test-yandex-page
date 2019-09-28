package site.steps;

import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SocialNetworkPage {
    @Autowired
    private WebDriver driver;

    @Autowired
    private CommonSteps commonSteps;

    @Then("^should be open (.*) auth page$")
    public void checAuthPage(String url) throws AWTException {
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

        commonSteps.checkUrl(url);

        Robot robot = new Robot();
        // press key Alt+F4
        robot.keyPress(KeyEvent.VK_ALT);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_F4);
        // relase key Alt+F4
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_F4);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_ALT);

        driver.switchTo().window(newTab.get(0));
    }
}
