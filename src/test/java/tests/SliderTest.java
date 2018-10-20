package tests;

import config.TestDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Test for dynamic jQuery slider
 *
 * @see <a href="https://jqueryui.com/slider/#custom-handle">jQuery Slider</a>
 * Configuration class for driver {@link  config.TestDriverConfig}
 */

public class SliderTest extends TestDriverConfig {

    private Random generator = new Random();


    /*
    Test case for all numbers between 0-100
     */
    @Test
    public void shouldMoveSliderByArrows() throws InterruptedException {
        WebElement slider = setUpWebElements();
        Integer value = generator.nextInt(100);

        slider.click();
        if (Integer.valueOf(slider.getText()) > value) {
            performActionDown(value, slider);
        } else if (Integer.valueOf(slider.getText()).equals(value)) {
        } else {
            performActionUp(value, slider);
        }
        Assert.assertEquals(slider.getText(), "100");
    }
     /*
     Test case for each Step :  80,50,55  with return 
      */
    @Test
    public void shouldCheckEachStep() throws InterruptedException {
        WebElement slider = setUpWebElements();
        Queue<Integer> integersQueue = new PriorityQueue<>(Arrays.asList(80, 50, 55));
        
        for (Integer inT : integersQueue) {
            if (Integer.valueOf(slider.getText()) > inT) {
                performActionDown(inT, slider);
            } else if (Integer.valueOf(slider.getText()).equals(inT)) {
            } else {
                performActionUp(inT, slider);
            }
            Assert.assertEquals(Integer.valueOf(slider.getText()), inT);
        }
    }

    private WebElement setUpWebElements() {
        webDriver.get("https://jqueryui.com/slider/#custom-handle");
        return webDriver.switchTo()
                .frame(webDriver.findElement(By.className("demo-frame")))
                .findElement(By.id("custom-handle"));
    }

    private void performActionUp(Integer value, WebElement slider) throws InterruptedException {
        while (!Integer.valueOf(slider.getText()).equals(value)) {
            Thread.sleep(125);
            slider.sendKeys(Keys.ARROW_UP);
        }
    }

    private void performActionDown(Integer value, WebElement slider) throws InterruptedException {
        while (!Integer.valueOf(slider.getText()).equals(value)) {
            Thread.sleep(125);
            slider.sendKeys(Keys.ARROW_DOWN);
        }
    }

}
