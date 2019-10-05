package site.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.ScenarioContext;
import site.util.SeleniumUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LogInPage {
//    @Autowired
    private WebDriver driver;

    public LogInPage(ScenarioContext scenarioContext) {
        driver = scenarioContext.getDriver();
    }

    final private String url = "https://passport.yandex.ru/auth?backpath=https%3A%2F%2Fyandex.ru";

    @Given("^open authorization page$")
    public void openAuthPage() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Map<String, String> titles = new HashMap<>();
        titles.put("Ru", "Авторизация");
        titles.put("En", "Authorization");

        driver.get(url);

        WebElement localization = SeleniumUtils.byXpath(driver, "//footer/div/div[2]/span[1]/span[1]/span");
        assertNotNull("Localazation not found", localization);

        String local = localization.getText();

        assertTrue(String.format("unknown local: %s", local), titles.containsKey(local));
        assertEquals(driver.getTitle(), titles.get(local));
    }

    @And("^input (.*): (.*)$")
    public void inputValue(String filed, String password) {
        String xPath = String.format("//*[@id=\"passp-field-%s\"]", filed);
        WebElement passwordField = SeleniumUtils.byXpath(driver, xPath);
        assertNotNull("Login field is not found", passwordField);

        passwordField.sendKeys(password);
    }

    @And("^press '(.*)' button$")
    public void pressButton(String buttonName) throws InterruptedException {
        String xPath = String.format("//span[contains(text(),'%s')]/..", buttonName);
        WebElement button = SeleniumUtils.byXpath(driver, xPath);
        assertNotNull(String.format("'%s' button is not found", buttonName), button);

        Thread.sleep(500);
        button.click();
    }

    @When("^click '(.*)' link$")
    public void clickLink(String linkText) throws InterruptedException {
        String xPath = String.format("//a[text()='%s']", linkText);

        WebElement link = SeleniumUtils.byXpath(driver, xPath);
        if (link == null) {
            xPath = String.format("//span[text()='%s']", linkText);
            link = SeleniumUtils.byXpath(driver, xPath);
        }
        assertNotNull(String.format("'%s' link is not found", linkText), link);

        Thread.sleep(500);

        link.click();
    }

    @Then("^should be shown any warning message:$")
    public void checkMessage(List<String> expectedMessages) {
        WebElement element = SeleniumUtils.byXpath(driver, "//*[@class=\"passp-form-field__error\"]");
        if (element == null) {
            element = SeleniumUtils.byXpath(driver, "//*[@class=\"passp-form-field__label\"]");
        }
        assertNotNull("Warning is not shown", element);

        String actualWarning = element.getText();

        boolean isWarningPresent = false;
        for (String expectedMessage : expectedMessages) {
            System.out.println(expectedMessage + " " + actualWarning);
            if (expectedMessage.equals(actualWarning)) {
                isWarningPresent = true;
                break;
            }
        }
        assertTrue("No expected message", isWarningPresent);
    }

    @Then("^should be shown login warning message: (.*)$")
    public void checkLoginWarning(String expectedWarning) {
        WebElement actualWarning = SeleniumUtils.byXpath(driver, "//*[@class=\"passp-form-field__error\"]");
        assertNotNull("Warning is not found", actualWarning);
        assertEquals(expectedWarning, actualWarning.getText().replaceAll("&nbsp", " "));
    }

    @Then("^'(.*)' page should be shown$")
    public void checkRegistrationPage(String title) {
        WebDriverWait wait5s = new WebDriverWait(driver, 5);
        assertTrue(String.format("%s is not opened", url), wait5s.until(ExpectedConditions.urlContains(url)));

        assertEquals(driver.getTitle(), title);
    }

    @Then("^should be shown message: '(.*)'$")
    public void checkMessage(String expectedMessage) throws InterruptedException {
        Thread.sleep(500);
        WebElement actualMessage = SeleniumUtils.byXpath(driver, "//*[@class=\"passp-title \"]");
        assertNotNull("No expected message", actualMessage);
        assertEquals(expectedMessage, actualMessage.getText().replaceAll("&nbsp", " "));
    }

    @Then("^should be shown captcha form$")
    public void checkCaptcha() {
        WebElement captcha = SeleniumUtils.byXpath(driver,
                "//*[@class=\"passp-form passp-enter-captcha-form\"]");
        assertNotNull("Captcha is not found", captcha);
    }

    @And("^click QR button$")
    public void callQrCode() {
        WebElement qrButton = SeleniumUtils.byXpath(driver,
                "//button[@class=\"control button2 button2_view_classic button2_size_l button2_theme_action button2_width_max passp-sign-in-button__magic-link\"]");
        assertNotNull("Captcha is not found", qrButton);

        qrButton.click();
    }

    @Then("^extend social network icons$")
    public void checkSocialNetwork() {
        WebElement extendSocialNetwork = SeleniumUtils.byXpath(driver,
                "//span[@class=\"passp-social-block__list-item-icon passp-social-block__list-item-icon_more\"]");
        assertNotNull("Captcha is not found", extendSocialNetwork);

        extendSocialNetwork.click();
    }

    @And("click (.*) network")
    public void chooseNetwork(String network) {
        String xPath = String
                .format("//span[@class=\"passp-social-block__list-item-icon passp-social-block__list-item-icon_%s\"]"
                        , network);
        WebElement networkLink = SeleniumUtils.byXpath(driver, xPath);
        assertNotNull("Network not found", networkLink);

        networkLink.click();
    }
}


