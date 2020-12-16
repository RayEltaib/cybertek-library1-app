package com.cybertek.library.utilities.ui;

import com.google.common.base.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BrowserUtils {

    public static void waits(int sec){
        try{
            Thread.sleep(1000L *sec);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param element .
     * @param timeToWaitInSeconds .
     * @return wait till element is visible
     * */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSeconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSeconds);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitTillClickable(WebElement element, int timeToWaitInSeconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSeconds);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static List<String> getElementsText(List<WebElement> list){
        List<String> elementsText = new ArrayList<>();
        for(WebElement element : list) {
            elementsText.add(element.getText());
        }
        return elementsText;
    }

    public void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    public static void verifyElementDisplayed(WebElement element) {
        try {
            assertTrue("Element not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            fail("Element not found: " + element);

        }
    }


    public static String getDropDownSelection(WebElement element){
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public static List<String> getDropDownOptions(WebElement element){
        Select select= new Select(element);
        List<WebElement> elements = select.getOptions();
        return getElementsText(elements);
    }

    public static WebElement fluentWait(final WebElement webElement, int timeInSec) {
        FluentWait<WebDriver> wait = new FluentWait<>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(timeInSec))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        return wait.until((Function<WebDriver, WebElement>) driver -> webElement);
    }


}
