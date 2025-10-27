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
<<<<<<< HEAD
  //monochrome = true,
  dryRun = true,
=======
  monochrome = true,
>>>>>>> e431b7714c2b31ac92cc24e267f7e8c8619bfa50
  tags = "@regression"
)
public class RunRegressionTests {}
