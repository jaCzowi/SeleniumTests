package tests;

import config.TestDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Test for working with IFrames using selenium 
 *
 * @see <a href="http://toolsqa.com/iframe-practice-page/">IFrames example</a>
 * Configuration class for driver {@link  config.TestDriverConfig}
 */
public class FrameTest extends TestDriverConfig {

    @Test
    public void shouldTestIFrame() throws InterruptedException {
        webDriver.get("http://toolsqa.com/");
        webDriver.findElement(By.xpath("//ul[@id='primary-menu']/li[8]/a/span/span/span")).click();
        new WebDriverWait(webDriver, 6000);
        webDriver.findElement(By.xpath("//ul[@id='primary-menu']/li[8]/ul/li[7]/a/span/span")).click();
        Thread.sleep(5000);
        WebDriver iframe1 = webDriver.switchTo().frame("iframe1");
        iframe1.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        webDriver.switchTo().defaultContent();
        WebDriver iframe2 = webDriver.switchTo().frame(webDriver.findElement(By.id("IF2")));
        iframe2.findElement(By.xpath("//div[@id='primary']/main/article/footer/p/a")).click();
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.xpath("//ul[@id='primary-menu']/li/a/span/span/span")).click();
    }
}
