package tests.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

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
  tags = "@regression"
)
public class RunRegressionTests {}
