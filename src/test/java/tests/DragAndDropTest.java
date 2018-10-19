package tests;


import config.TestDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test for dynamic D'n'D activity in jQuery elements
 *
 * @see <a href="https://jqueryui.com/droppable/">D'n'D example</a>
 * Configuration class for driver {@link  config.TestDriverConfig}
 */
public class DragAndDropTest extends TestDriverConfig {


    @Test
    public void shouldDragNDropItem() throws InterruptedException {
        webDriver.get("https://jqueryui.com/droppable/");
        webDriver.switchTo()
                .frame(returnFrame());

        WebElement placeHold = webDriver.findElement(By.id("droppable")).findElement(By.tagName("p"));
        // check before and action 
        Assert.assertEquals(placeHold.getText(), "Drop here");

        new Actions(webDriver)
                .dragAndDrop(webDriver.findElement(By.id("draggable")), placeHold)
                .build().perform();
        // after D'n'D
        Thread.sleep(800);
        Assert.assertEquals(placeHold.getText(), "Dropped!");
    }

    private WebElement returnFrame() {
        return webDriver.findElement(By.className("demo-frame"));
    }
}
