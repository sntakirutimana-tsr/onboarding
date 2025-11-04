package pages.products.components;

import pages.Concerns;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;
import org.jspecify.annotations.NonNull;

import java.util.List;

import static utils.Formatters.f;

public abstract class Component extends Concerns {
  private final By rootLocator;
  private final WebElement rootElement;

  public Component(WebDriver webDriver, By rootLocator, WebElement rootElement) {
    super(webDriver);
    this.rootLocator = rootLocator;
    this.rootElement = rootElement;
  }

  protected WebElement getRootElement() {
    return rootElement;
  }

  protected WebElement waitForInteractivenessOf(By locator) {
    WebElement nestedElement = findBy(locator);
    return getWait().until(ExpectedConditions.elementToBeClickable(nestedElement));
  }

  protected void waitForStalenessOf(WebElement element) {
    getWait().until(ExpectedConditions.stalenessOf(element));
  }

  protected WebElement findBy(By locator) {
    if (rootLocator == null && rootElement == null)
      return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    if (rootElement == null)
      return getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(rootLocator, locator));
    return getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(rootElement, locator));
  }

  protected List<WebElement> findAllBy(By locator) {
    if (rootLocator == null && rootElement == null)
      return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    if (rootElement == null)
      return getWait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(rootLocator, locator));
    return getWait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(rootElement, locator));
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
    WebElement self = findBy(locator);
    new Select(self).selectByContainsVisibleText(value);
    waitForStalenessOf(self);
  }

  public void selectByContainsVisibleTextFromDropdown(By locator, @NonNull String value) {
    WebElement self = findBy(locator);
    click(locator);
    click(By.xpath(f("//option[contains(text(), '{}')]", value)));
    waitForStalenessOf(self);
  }
}
