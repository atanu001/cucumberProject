package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/Application.feature", glue = { "stepDefinitions",
		"AppHooks" }, tags = "@Step", monochrome = true, publish = true)

public class MyTestRunner {

}
