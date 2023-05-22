package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "@rerunFailedTCs/failed_testcase_scenarios.txt" }, plugin = {
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, glue = {
				"stepdefinition" }, dryRun = false)
public class RerunTestRunnerFile {
}
