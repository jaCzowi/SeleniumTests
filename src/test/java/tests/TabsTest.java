package tests;


import config.TestDriverConfig;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class test for Tab elements in selenium webDriver
 *
 * @see <a href="http://toolsqa.com/automation-practice-switch-windows/">Tabs example</a>
 * Configuration class for driver {@link  config.TestDriverConfig}
 */
public class TabsTest extends TestDriverConfig {

    @BeforeMethod(dependsOnGroups = "windows")
    private void setUpDriverToAlert() {
        webDriver.get("http://toolsqa.com/automation-practice-switch-windows/");
    }

    @Test(groups = "windows")
    public void shouldTestBrowserWindow() throws InterruptedException {
        webDriver.findElement(By.id("button1")).click();
        Set<String> windowHandles = webDriver.getWindowHandles();

        WebDriver window = webDriver.switchTo().window((String) windowHandles.toArray()[1]);
        WebElement imgElement = window.findElement(By.xpath("//img[contains(@alt,'Selenium Tutorial in Java')]"));
        Thread.sleep(1500);
        imgElement.click();
        Thread.sleep(1500);
        window.close();
        // switch back
        WebDriver window1 = webDriver.switchTo().window((String) windowHandles.toArray()[0]);
        window1.findElement(By.id("alert")).click();
        Thread.sleep(1500);
        Alert alert1 = webDriver.switchTo().alert();
        alert1.accept();
    }

    @Test(groups = "windows")
    public void shouldTestMessageWindow() throws InterruptedException {
        webDriver.findElement(By.xpath("//div[@id='content']/p[3]/button")).click();
        Set<String> windowHandles = webDriver.getWindowHandles();

        WebDriver msgWindow = webDriver.switchTo().window((String) windowHandles.toArray()[1]);
        String bodyMsg = msgWindow.findElement(By.cssSelector("body")).getText();
        String expected = "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.";
        Assert.assertEquals(expected, bodyMsg);

        // closing msgWindow must be before switch ! 
        msgWindow.close();

        WebDriver window = webDriver.switchTo().window((String) windowHandles.toArray()[0]);
        window.findElement(By.id("colorVar")).click();

        // sleep to change color 
        Thread.sleep(5000);
        WebElement colorButton = window.findElement(By.id("colorVar"));
        String style = colorButton.getAttribute("style");
        Assert.assertEquals(style, "color: red;");
        Thread.sleep(1500);
    }

    @Test
    public void shouldTestBrowserTab() throws InterruptedException {
        webDriver.findElement(By.xpath("//div[@id='content']/p[4]/button")).click();

        List<String> webHandlers = new ArrayList<>(webDriver.getWindowHandles());
        WebDriver secondWindow = webDriver.switchTo().window(webHandlers.get(1));
        secondWindow.findElement(By.xpath("//ul[@id='primary-menu']/li[5]/a/span/span/span")).click();
        Thread.sleep(5000);
        List<String> secondWebHandlers = new ArrayList<>(secondWindow.getWindowHandles());
        secondWindow.switchTo().window(secondWebHandlers.get(2)).close();
        Thread.sleep(2500);
        secondWindow.switchTo().window(secondWebHandlers.get(1)).close();
        Thread.sleep(1000);
        webDriver.switchTo().window(secondWebHandlers.get(0));
    }
}
