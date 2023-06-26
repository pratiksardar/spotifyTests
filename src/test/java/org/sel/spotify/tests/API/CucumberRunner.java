package org.sel.spotify.tests.API;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.sel.spotify.base.APIBaseTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
glue = "org/sel/spotify/stepDefinitions")
public class CucumberRunner extends APIBaseTest {
}
