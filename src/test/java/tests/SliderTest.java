package tests;

import config.TestDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import java.util.Random;

/**
 * Test for dynamic jQuery slider
 *
 * @see <a href="https://jqueryui.com/slider/#custom-handle">jQuery Slider</a>
 * Configuration class for driver {@link  config.TestDriverConfig}
 */

public class SliderTest extends TestDriverConfig {

    private Random generator = new Random();

    @Test
    public void shouldMoveSliderByArrows() throws InterruptedException {
        webDriver.get("https://jqueryui.com/slider/#custom-handle");
        WebElement slider = webDriver.switchTo()
                .frame(webDriver.findElement(By.className("demo-frame")))
                .findElement(By.id("custom-handle"));

        Integer value = generator.nextInt(1) + 99;
        

        slider.click();
        if (Integer.valueOf(slider.getText()) > value) {
            performActionDown(value, slider);
        } else if (Integer.valueOf(slider.getText()).equals(value)) {
            System.out.println("Same-no action performed");
        } else {
            performActionUp(value, slider);
        }
        Assert.assertEquals(slider.getText(),"99" );
    }


    private void performActionUp(Integer value, WebElement slider) throws InterruptedException {
        while (!Integer.valueOf(slider.getText()).equals(value)) {
            Thread.sleep(100);
            slider.sendKeys(Keys.ARROW_UP);
        }
    }

    public void performActionDown(Integer value, WebElement slider) throws InterruptedException {
        while (!Integer.valueOf(slider.getText()).equals(value)) {
            Thread.sleep(100);
            slider.sendKeys(Keys.ARROW_DOWN);
        }
    }


}
