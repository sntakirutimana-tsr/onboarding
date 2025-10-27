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
<<<<<<< HEAD
  //monochrome = true,
  dryRun = true,
=======
  monochrome = true,
>>>>>>> e431b7714c2b31ac92cc24e267f7e8c8619bfa50
  tags = "@smoke"
)
public class RunSmokeTests {}
