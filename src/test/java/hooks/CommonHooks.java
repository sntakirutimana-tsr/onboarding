package hooks;

import com.utils.DriverProvider;
import com.utils.RunContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import org.openqa.selenium.WebDriver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonHooks {

  @Before
  public void setup() {
    DriverProvider.get();
  }

  @After
  public void teardown() {
    RunContext.clear();
    WebDriver webDriver = DriverProvider.get();
    if (webDriver != null) {
      try {
        webDriver.manage().deleteAllCookies();
        //webDriver.quit();
      } catch (Exception e) {
        log.error("Error during driver cleanup: {}", e.getMessage());
      } finally {
        DriverProvider.remove();
      }
    }
  }
}
