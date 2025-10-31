package pages.products.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Component {
  private final WebDriver driver;
  private final Object root;

  public Component(WebDriver driver, WebElement root) {
    this.driver = driver;
    this.root = root;
    PageFactory.initElements(root, this);
  }

  public Component(WebDriver driver) {
    this.driver = driver;
    this.root = null;
    PageFactory.initElements(driver, this);
  }

  protected WebElement getRoot() {
    return (WebElement) root;
  }

  protected WebDriver getDriver() {
    return driver;
  }

  public abstract void ensureIsReady();

  protected WebElement findBy(By by) {
    return root == null ? driver.findElement(by) : getRoot().findElement(by);
  }

  protected String getText(WebElement element) {
    return element.getText();
  }
}
