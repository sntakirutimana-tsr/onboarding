package com.pages.product.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class PriceRangeFilter extends Component {
  @FindBy(css = ".price_slider span.ui-slider-handle:nth-of-type(1)")
  private WebElement minHandle;

  @FindBy(css = ".price_slider span.ui-slider-handle:nth-of-type(2)")
  private WebElement maxHandle;

  @FindBy(xpath = "//[@id='woocommerce_price_filter-3']//button[@type='submit' and text()='Filter']")
  private WebElement button;

  @FindBy(css = "#woocommerce_price_filter-3 .price_label")
  private WebElement priceLabel;

  public PriceRangeFilter(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  public String getPriceLabel() {
    return priceLabel.getText();
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForVisibility(findElement(By.xpath("//h2[text()='Filter by price']")), 5);
    waitForVisibility(findElement(By.cssSelector("div.price_slider")), 5);
    waitForElementToBeInteractive(button, 5);
    waitForTextVisibility(priceLabel, "Price: $10 — $150", 5);
  }
}
