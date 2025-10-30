package tests.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "classpath:features",
  glue = {"tests.steps", "tests.hooks"},
  plugin = {
    "pretty",
    "html:target/cucumber-reports.html",
    "junit:target/cucumber.xml"
  },
  monochrome = true,
//  dryRun = true,
  tags = "@smoke"
)
public class RunSmokeTests {}
