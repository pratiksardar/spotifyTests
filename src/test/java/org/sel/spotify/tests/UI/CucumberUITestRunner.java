package org.sel.spotify.tests.UI;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
//import org.sel.spotify.base.UIBaseTest;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/features/UITests.feature",
//        glue = "stepdefinitions"
//)
//public class CucumberUITestRunner extends UIBaseTest {
//}

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/UITests.feature",
        glue = "org/sel/spotify/stepDefinitions"
)
public class CucumberUITestRunner extends AbstractTestNGCucumberTests {
}
