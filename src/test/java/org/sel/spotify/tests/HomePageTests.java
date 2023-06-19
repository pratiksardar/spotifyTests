package org.sel.spotify.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.sel.spotify.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.sel.spotify.utils.AppConstants;

import java.util.List;

public class HomePageTests extends BaseTest {

    @Description("checking home page title test...")
    @Feature("title test")
    @Test
    public void homePageTitleTest() {
        String actTitle = homePage.getLoginPageTitle();
        Assert.assertEquals(actTitle, AppConstants.HOMEPAGE_TITLE);
    }

    @Test
    public void getHeadingsTests() {
        List<String> headings = homePage.getHeadings();
        System.out.println(headings);
    }

    @Test
    public void gotoSearchSection() {
        homePage.navigateToSearchPage();

    }
}
