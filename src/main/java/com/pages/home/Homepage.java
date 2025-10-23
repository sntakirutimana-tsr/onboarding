package com.pages.home;

import com.pages.Page;
import com.pages.product.*;
import com.utils.DriverProvider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class Homepage extends Page {
  @FindBy(id = "menu-item-1227")
  private WebElement storeOption;

  @FindBy(id = "menu-item-1228")
  private WebElement menOption;

  @FindBy(id = "menu-item-1229")
  private WebElement womenOption;

  @FindBy(id = "menu-item-1230")
  private WebElement accessoriesOption;

  public Homepage(WebDriver driver) {
    super(driver);
  }

  @Override
  public void prepareIsLoadedCheckpoints() {
    waitForVisibility(storeOption, 5);
    waitForVisibility(menOption, 3);
    waitForVisibility(womenOption, 3);
    waitForVisibility(accessoriesOption, 3);
  }

  public ProductsPage browseProducts(String pageName) {
    String page = pageName.replaceAll("'s$", "").toLowerCase();
    switch (page) {
      case "men" -> menOption.click();
      case "women" -> womenOption.click();
      case "accessories" -> accessoriesOption.click();
      case "store" -> storeOption.click();
      default -> throw new IllegalArgumentException("Unknown menu option: " + pageName);
    }
    return ProductsPage.buildFor(page, driver);
  }

  public static Homepage visit() {
    WebDriver driver = DriverProvider.get();
    driver.get("https://askomdch.com/");
    return new Homepage(driver);
  }
}
