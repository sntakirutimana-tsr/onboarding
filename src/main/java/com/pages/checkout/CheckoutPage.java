package com.pages.checkout;

import com.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public abstract class CheckoutPage extends Page {

  private final By shipToDifferentAddressCheckbox = By.id("ship-to-different-address-checkbox");
  private final By placeOrderButton = By.id("place_order");
  private final By errorMessages = By.cssSelector(".woocommerce-error li");

  public CheckoutPage(WebDriver driver) {
    super(driver);
  }

  public void navigateToCheckout() {
    driver.get(Page.BASE_URL+"/checkout/");
  }

  public void checkShipToDifferentAddress() {
    WebElement checkbox = driver.findElement(shipToDifferentAddressCheckbox);
    if (!checkbox.isSelected()) checkbox.click();
  }

  public boolean isShippingFormDisplayed() {
    return driver.findElement(By.id("shipping_first_name")).isDisplayed();
  }

  public void clickPlaceOrder() {
    driver.findElement(placeOrderButton).click();
  }

  public List<WebElement> getErrorMessages() {
    return driver.findElements(errorMessages);
  }
}
