package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.jspecify.annotations.NonNull;

import static utils.Envs.BASE_URI;
import static utils.Formatters.f;

public abstract class BasePage extends Concerns {

  public BasePage(WebDriver webDriver) {
    super(webDriver);
  }

  public void visit(String url) {
    getDriver().get(f("{}/{}", BASE_URI, url));
  }

  public WebElement findBy(By locator) {
    return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public void click(By locator) {
    waitForInteractivenessOf(locator).click();
  }

  public String getText(By locator) {
    return findBy(locator).getText();
  }

  public void type(By locator, @NonNull String value) {
    waitForInteractivenessOf(locator).sendKeys(value);
  }

  public void selectByContainsVisibleText(By locator, @NonNull String value) {
    Select selector = new Select(findBy(locator));
    selector.selectByContainsVisibleText(value);
  }

  protected WebElement waitForInteractivenessOf(By locator) {
    return getWait().until(ExpectedConditions.elementToBeClickable(locator));
  }

  protected void waitForStalenessOf(WebElement element) {
    getWait().until(ExpectedConditions.stalenessOf(element));
  }
}
