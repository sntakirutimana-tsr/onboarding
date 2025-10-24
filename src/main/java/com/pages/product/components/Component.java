package com.pages.product.components;

import com.pages.concerns.Waits;
import com.utils.Executor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.Getter;

import java.lang.reflect.Proxy;
import java.util.List;

public abstract class Component extends Waits {
  @Getter
  private final WebDriver driver;
  @Getter
  private final WebElement root;

  public Component(WebDriver driver, WebElement root) {
    super();
    this.driver = driver;
    this.root = root;
    this.initiateElements();
  }

  public abstract void ensureAllCheckpointsAreReady();

  protected void initiateElements() {
  }

  protected WebElement findElement(By by) {
    return (WebElement) Proxy.newProxyInstance(
      WebElement.class.getClassLoader(),
      new Class[]{WebElement.class},
      (proxy, method, args) -> {
        WebElement element = root.findElement(by);
        return method.invoke(element, args);
      }
    );
  }

  protected String getText(WebElement element) {
    return element.getText();
  }

  protected List<WebElement> findElements(By by) {
    return getRoot().findElements(by);
  }

  protected void ensureExistenceOfElement(By by) {
    Executor.raiseIf(() -> findElement(by).isDisplayed(), "No element found for locator: " + by);
  }

  protected void ensureExistenceOfElement(WebElement element) {
    Executor.raiseIf(() -> element != null && element.isDisplayed());
  }
}
