package org.sel.spotify.tests.UI;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.sel.spotify.base.UIBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.sel.spotify.utils.AppConstants;

import java.util.List;

public class HomePageTests extends UIBaseTest {

    @Description("checking home page title test...")
    @Feature("title test")
    @Test(priority = 0)
    public void homePageTitleTest() {
        String actTitle = homePage.getLoginPageTitle();
        Assert.assertEquals(actTitle, AppConstants.HOMEPAGE_TITLE);
    }

    @Test(priority = 1)
    public void getHeadingsTests() {
        List<String> headings = homePage.getHeadings();
        System.out.println(headings);
    }

    @Test
    public void gotoSearchSection() {
        homePage.navigateToSearchPage();

    }
}
