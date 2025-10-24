package tests.hooks;

import com.pages.product.components.SortBy;
import com.utils.DriverProvider;
import com.utils.LoggerFactoryUtil;
import com.utils.RunContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import org.openqa.selenium.WebDriver;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

public class CommonHooks {
  private static final Logger log = LoggerFactoryUtil.getLogger(CommonHooks.class);

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
        webDriver.quit();
      } catch (Exception e) {
        log.error("Error during driver cleanup: {}", e.getMessage());
      } finally {
        DriverProvider.remove();
      }
    }
  }
}
