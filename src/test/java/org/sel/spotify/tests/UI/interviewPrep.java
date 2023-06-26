package org.sel.spotify.tests.UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class interviewPrep  {

    private WebDriver driver;


    @BeforeTest
    private  void setup(){
        driver = new ChromeDriver();
        driver.get("https://amazon.in");
    }

    @Test
    private void searchProduct(){

    }





}
