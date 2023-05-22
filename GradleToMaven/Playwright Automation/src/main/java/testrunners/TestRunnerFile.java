package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@All",
		// Tags are associated with scenario's which are in feature file
		features = { "src/test/java/featurefiles" }, plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:rerunFailedTCs/failed_testcase_scenarios.txt" },

		glue = { "stepdefinition" }, dryRun = false)
public class TestRunnerFile {
}