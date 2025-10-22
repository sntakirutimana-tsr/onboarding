package com.pages.product.components;

import com.utils.FormatUtils;
import com.utils.Executor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class PriceRangeFilter extends Component {
  private WebElement minHandle;
  private WebElement maxHandle;
  private WebElement button;
  private WebElement priceLabel;

  private final int[] initialPriceRange;

  public PriceRangeFilter(WebDriver driver, WebElement root, int...initialPriceRange) {
    super(driver, root);
    this.initialPriceRange = initialPriceRange;
  }

  @Override
  protected void initiateElements() {
    minHandle = findElement(By.cssSelector(".price_slider span.ui-slider-handle:nth-of-type(1)"));
    maxHandle = findElement(By.cssSelector(".price_slider span.ui-slider-handle:nth-of-type(2)"));
    button = findElement(By.xpath("//button[@type='submit' and text()='Filter']"));
    priceLabel = findElement(By.className("price_label"));
  }

  public String getPriceLabel() {
    return priceLabel.getText();
  }

  String getPriceLabel(int min, int max) {
    return FormatUtils.f("Price: ${} — ${}", min, max);
  }

  void hasPriceLabel(int min, int max) {
    String label = getPriceLabel(min, max);
    Executor.raiseIf(() -> getPriceLabel().equals(label), FormatUtils.f("Expected price filter label ❝{}❞", label));
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    hasElement(By.xpath("//h2[text()='Filter by price']"));
    hasElement(By.cssSelector("div.price_slider"));
    hasElement(minHandle);
    hasElement(maxHandle);
    waitForElementToBeInteractive(button, 5);
    hasPriceLabel(initialPriceRange[0], initialPriceRange[1]);
  }
}
