package com.pages;

import com.pages.concerns.Waits;
import com.utils.Executor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import lombok.Getter;

public abstract class Page extends Waits {
  @Getter
  protected final WebDriver driver;

  public static final String BASE_URL = "https://askomdch.com/";

  public Page(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public final boolean isLoaded() {
    return Executor.hasEvaluatedAndSucceed(this::prepareIsLoadedCheckpoints);
  }

  protected WebElement findElement(By by) {
    return getDriver().findElement(by);
  }

  protected abstract void prepareIsLoadedCheckpoints();
}
