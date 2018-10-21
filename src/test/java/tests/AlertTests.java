package tests;

import config.TestDriverConfig;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;


/**
 * Class for Handling-test of all possible Alerts
 *
 * @see <a href="http://toolsqa.com/handling-alerts-using-selenium-webdriver/">Handling Alerts</a>
 * Configuration class for driver {@link  config.TestDriverConfig}
 */
public class AlertTests extends TestDriverConfig {

    @BeforeMethod
    private void setUpDriverToAlert() {
        webDriver.get("http://toolsqa.wpengine.com/handling-alerts-using-selenium-webdriver/");
    }

    @Test
    public void shouldTestBasicAlert() throws InterruptedException {
        WebElement button = webDriver.findElement(By.xpath("//*[@id='content']/p[4]/button"));
        button.click();
        Alert alert = webDriver.switchTo().alert();
        String alertText = alert.getText();
        //Then
        Assert.assertEquals("A simple Alert", alertText);
        Thread.sleep(1000);
        alert.accept();
        Thread.sleep(300);
    }

    @Test
    public void shouldTestConfirmAlert() throws InterruptedException {
        WebElement confirmButton = webDriver.findElement(By.xpath("//button[contains(text(),'Confirm Pop up')]"));
        confirmButton.click();
        //Then
        Alert confirmAlert = webDriver.switchTo().alert();
        String text = confirmAlert.getText();
        Assert.assertEquals("Confirm pop up with OK and Cancel button", text);
        if (new Random().nextInt(10) > 5) {
            Thread.sleep(1000);
            confirmAlert.dismiss();
            Thread.sleep(500);
        } else {
            Thread.sleep(1000);
            confirmAlert.accept();
            Thread.sleep(500);
        }
    }

    @Test
    public void shouldTestPromptAlert() throws InterruptedException {
        WebElement button = webDriver.findElement(By.xpath("//button[contains(text(),'Prompt Pop up')]"));
        button.click();
        //Then
        Alert promptAlert = webDriver.switchTo().alert();
        String text = promptAlert.getText();
        Assert.assertEquals("Do you like toolsqa?", text);
        promptAlert.sendKeys("No");
        Thread.sleep(1000);
        promptAlert.accept();
    }
}
