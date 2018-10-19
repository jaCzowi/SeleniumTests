package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Configuration class providing and manage drivers for tests
 */
public class TestDriverConfig {

    protected WebDriver webDriver;

    @BeforeMethod
    public void setWebDriverProperties() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions().addArguments("start-maximized");
        webDriver = new ChromeDriver(chromeOptions);
    }


    @AfterMethod
    public void closeWebDriver() {
        webDriver.close();
    }
}
