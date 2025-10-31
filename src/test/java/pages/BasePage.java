package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigLoader;

import java.time.Duration;

public class BasePage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    PageFactory.initElements(driver, this);

  }

  public void load(String url) {
    driver.get(ConfigLoader.getInstance().getBaseUrl() + url);
  }

  public WebElement findBy(By locator) {
    return driver.findElement(locator);
  }

  public void click(WebElement element) {
    element.click();
  }

  public void click(By locator) {
    findBy(locator).click();
  }

  public String getText(WebElement element) {
    return element.getText();
  }

  public String getAttribute(WebElement element, String name) {
    return element.getAttribute(name);
  }

  public void type(WebElement element, String value) {
    if (value == null) value = "";
    element.clear();
    element.sendKeys(value);
  }

  public <T> T waitFor(ExpectedCondition<T> condition) {
    return wait.until(condition);
  }
}
