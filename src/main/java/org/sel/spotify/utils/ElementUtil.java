package org.sel.spotify.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sel.spotify.factory.DriverFactory;

import java.time.Duration;
import java.util.List;

public class ElementUtil {

    private final WebDriver driver;
    private final JavaScriptUtil jsUtil;
    private final int DEFAULT_TIME_OUT = 5;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        jsUtil = new JavaScriptUtil(this.driver);
    }

    public String waitForTitleIsAndCapture(String titleFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
            String title = driver.getTitle();
            return title;
        }
        else {
            System.out.println("title is not present within the given timeout : " + timeOut);
            return null;
        }
    }

    public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if(Boolean.parseBoolean(DriverFactory.highlightElement)) {
            jsUtil.flash(element);
        }
        return element;
    }

    public WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
            System.out.println("element is found with locator: " + locator);
        } catch (NoSuchElementException e) {
            System.out.println("Element is not found using this locator..." + locator);
            element = waitForElementVisible(locator, DEFAULT_TIME_OUT);
        }

        if(Boolean.parseBoolean(DriverFactory.highlightElement)) {
            jsUtil.flash(element);
        }
        return element;
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }


}
