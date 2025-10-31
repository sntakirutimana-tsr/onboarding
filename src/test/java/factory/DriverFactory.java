package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
  private static WebDriver driver;

  public static WebDriver initializeDriver(String browser) {

    switch (browser) {
      case "chrome":
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        break;
      case "edge":
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        break;
      case "firefox":
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        break;
      default:
        throw new IllegalArgumentException("INVALID BROWSER: " + browser);
    }

    driver.manage().window().maximize();
    return driver;
  }

  public static WebDriver getDriver() {
    return driver;
  }

}
