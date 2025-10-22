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
    return switch (pageName.replaceAll("'s$", "").toLowerCase()) {
      case "men" -> navigateToMen();
      case "women" -> navigateToWomen();
      case "accessories" -> navigateToAccessories();
      case "store" -> navigateToStore();
      default -> throw new IllegalArgumentException("Unknown menu option: " + pageName);
    };
  }

  ProductsPage navigateToMen() {
    menOption.click();
    return new MenProductsPage("Men", getDriver());
  }

  ProductsPage navigateToStore() {
    storeOption.click();
    return new StorePage("Store", getDriver());
  }

  ProductsPage navigateToAccessories() {
    accessoriesOption.click();
    return new AccessoriesProductsPage("Accessories", getDriver());
  }

  ProductsPage navigateToWomen() {
    womenOption.click();
    return new WomenProductsPage("Women", getDriver());
  }

  public static Homepage visit() {
    WebDriver driver = DriverProvider.get();
    driver.get("https://askomdch.com/");
    return new Homepage(driver);
  }
}
