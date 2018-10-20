package tests;

import config.TestDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ComplexParametrizedCalendarTest extends TestDriverConfig {


    /**
     * Date parameters provided from {@link TestDriverConfig}  class
     *
     * @param dates - calendar dates
     */
    @Test(dataProvider = "dateParameters")
    public void testJQueryDatePicket(String dates) throws InterruptedException {

        webDriver.get("http://jqueryui.com/datepicker/");
        webDriver.switchTo().frame(0);
        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        //set up datepicker by click
        webDriver.findElement(By.id("datepicker")).click();
        boolean dateNotFound = true;

        // Expected Date, Month and Year
        int expMonth = Integer.parseInt(dates.substring(3, 5));
        int expYear = Integer.parseInt(dates.substring(6));
        String expDay = dates.substring(0, 2);

        while (dateNotFound) {

            // get actual month and year
            String calMonth = webDriver.findElement(By.className("ui-datepicker-month")).getText();
            String calYear = webDriver.findElement(By.className("ui-datepicker-year")).getText();
            if (getMonthsAsNumber(calMonth) == expMonth && (expYear == Integer.parseInt(calYear))) {
                setDayForDate(Integer.parseInt(expDay));
                dateNotFound = false;
            } else if (getMonthsAsNumber(calMonth) < expMonth && (expYear == Integer.parseInt(calYear)) || expYear > Integer.parseInt(calYear)) {
                webDriver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
            } else if (getMonthsAsNumber(calMonth) > expMonth && (expYear == Integer.parseInt(calYear)) || expYear < Integer.parseInt(calYear)) {
                webDriver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
            }
        }
        Thread.sleep(2000);
    }

    private void setDayForDate(Integer date) {
        WebElement dateWidget = webDriver.findElement(By.id("ui-datepicker-div"));
        
        dateWidget.findElements(By.tagName("td"))
                .parallelStream()
                //.peek(t -> System.out.println("cell  " + t.getText()))
                .filter(cell -> cell.getText().equals(String.valueOf(date)))
                .findAny()
                .get()
                .findElement(By.linkText(String.valueOf(date))).click();
    }

    private int getMonthsAsNumber(String month) {
        return Arrays.asList("January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December")
                .indexOf(month) + 1;
    }

}
