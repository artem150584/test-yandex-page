package site;

import org.openqa.selenium.WebDriver;

public class ScenarioContext {
    private Thread shutDownHook;
    private WebDriver driver;

    synchronized void registerShutdownHook(Thread thread) throws IllegalAccessException {
        if (shutDownHook != null) {
            throw new IllegalAccessException("shut down hook");
        }
        shutDownHook = thread;
        Runtime.getRuntime().addShutdownHook(shutDownHook);
    }

    synchronized boolean isShutdownHookRegistred() {
        return shutDownHook != null;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
