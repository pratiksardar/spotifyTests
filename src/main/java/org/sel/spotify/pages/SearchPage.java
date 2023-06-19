package org.sel.spotify.pages;

import org.openqa.selenium.WebDriver;
import org.sel.spotify.utils.ElementUtil;

public class SearchPage {

    private final WebDriver driver;
    private final ElementUtil eleUtil;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }


}
