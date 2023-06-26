package org.sel.spotify.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sel.spotify.base.UIBaseTest;
import org.openqa.selenium.chrome.ChromeDriver;

import org.sel.spotify.factory.DriverFactory;
import org.sel.spotify.pages.HomePage;
import org.sel.spotify.utils.AppConstants;
import org.testng.Assert;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class UIStepDefinitions extends UIBaseTest{

    private WebDriver driver;
    private String browser;

    @Given("I am on the Spotify website")
    public void openSpotifyWebsite() {
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        System.out.println("Started");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://open.spotify.com");
        System.out.println("completed");
    }

    @When("I search for {string}")
    public void searchForArtist(String artist) {
        System.out.println("here");
//        WebElement searchInput = driver.findElement(By.xpath("//input[aria-label='Search']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//        WebElement searchIcon = driver.findElement();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href = \'/search\']/span")));
        element.click();
        System.out.println("clicked");
        WebElement searchInput = driver.findElement(By.cssSelector("input[aria-label='Search']"));
        searchInput.sendKeys(artist);
        WebElement searchForm = driver.findElement(By.cssSelector("form[role='search']"));
        searchForm.sendKeys(artist);
        searchForm.submit();
    }

    @Then("I should see search results related to {string}")
    public void verifySearchResults(String artist) {
        WebElement searchResults = driver.findElement(By.cssSelector(".search-topbar"));
        assertTrue(searchResults.getText().contains(artist));
    }

    public void tearDown() {
        driver.quit();
    }
    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }
}

