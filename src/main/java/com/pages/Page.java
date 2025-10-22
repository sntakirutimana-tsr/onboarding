package com.pages;

import com.pages.concerns.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import lombok.Getter;

public abstract class Page extends Waits {
  @Getter
  protected final WebDriver driver;

  public Page(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public final boolean isLoaded() {
    try {
      prepareIsLoadedCheckpoints();
      return true;
    } catch (Throwable e) {
      return false;
    }
  }

  protected WebElement findElement(By by) {
    return getDriver().findElement(by);
  }

  protected abstract void prepareIsLoadedCheckpoints();
}
