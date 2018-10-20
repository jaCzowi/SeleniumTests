package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public void closeWebDriver() throws InterruptedException {
        Thread.sleep(500);
        webDriver.close();
    }

    @DataProvider(name = "dateParameters")
    public Object[] dateParametersProvider() {
        String format = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
        Object[] dates = new Object[5];
        dates[0] = "01.02.2019";
        dates[1] = "27.12.2018";
        dates[2] = format;
        dates[3] = "06.07.2018";
        dates[4] = "20.11.2017";
        return dates;
    }
}
