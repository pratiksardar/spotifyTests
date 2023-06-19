package org.sel.spotify.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sel.spotify.utils.AppConstants;
import org.sel.spotify.utils.ElementUtil;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private final ElementUtil eleUtil;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    private final By home = By.xpath("//span[contains(.,'Home')]");

    private final By search = By.xpath("//span[contains(.,'Search')]");

    private final By headingsLocator = By.xpath("//h2/a");

    @Step("getting login page title....")
    public String getLoginPageTitle() {
        return eleUtil.waitForTitleIsAndCapture(AppConstants.HOMEPAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
    }

    @Step("get headdings...")
    public List<String> getHeadings(){
        List<WebElement> headings = eleUtil.waitForElementsVisible(headingsLocator,
                AppConstants.MEDIUM_DEFAULT_WAIT);
        List<String> headingsList = new ArrayList<>();
        for (WebElement e : headings) {
            String text = e.getText();
            headingsList.add(text);
        }
        return headingsList;
    }

    public HomePage navigateToHomePage() {
        eleUtil.doClick(home);
        return new HomePage(driver);
    }

    public SearchPage navigateToSearchPage() {
        eleUtil.doClick(search);
        return new SearchPage(driver);
    }


}
