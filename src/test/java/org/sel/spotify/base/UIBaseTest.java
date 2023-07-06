package org.sel.spotify.base;

import org.openqa.selenium.WebDriver;
import org.sel.spotify.factory.DriverFactory;
import org.sel.spotify.pages.HomePage;
import org.sel.spotify.pages.SearchPage;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class UIBaseTest {
    WebDriver driver;
    protected HomePage homePage;
    protected SearchPage searchPage;
    protected DriverFactory df;
    protected Properties prop;

    protected SoftAssert softAssert;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(String browserName) {
        df = new DriverFactory();
        prop = df.initProp();
        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }
        driver = df.initDriver(prop);

        homePage = new HomePage(driver);
        softAssert = new SoftAssert();
    }



    @AfterMethod
        public void tearDown() {
            driver.quit();
        }
    }

