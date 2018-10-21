package tests;

import config.TestDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


/**
 * Parameterized test for Calendar example  with datepicker.
 *
 * @see <a href="http://jqueryui.com/datepicker/">DatePicker</a>
 * Configuration class for driver {@link  config.TestDriverConfig}
 */
public class ParameterizedCalendarTest extends TestDriverConfig {

    /**
     * Date parameters provided from {@link TestDriverConfig}  class
     * @param dates - calendar dates 
     */
    @Test(dataProvider = "dateParameters")
    public void shouldTestAllDatesEasyWay(String dates) {
        WebDriver driverInFrame = getObjectFromFrame();
        setDatePicker(driverInFrame, dates);
    }
    private void setDatePicker(WebDriver driver, String date) {
        new WebDriverWait(driver, 20000).until(
                (WebDriver d) -> d.findElement(By.cssSelector("#datepicker")).isDisplayed());
        
        ((JavascriptExecutor) driver).executeScript(
                String.format("$('%s').datepicker('setDate', '%s')", "#datepicker", date));
    }
    private WebDriver getObjectFromFrame() {
        webDriver.get("http://jqueryui.com/datepicker/");
        return webDriver.switchTo()
                .frame(webDriver.findElement(By.cssSelector("iframe.demo-frame")));
    }
}
