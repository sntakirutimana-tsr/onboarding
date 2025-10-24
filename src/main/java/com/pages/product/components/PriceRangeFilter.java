package com.pages.product.components;

import com.pages.Page;
import com.pages.product.ProductsPage;
import com.utils.FormatUtils;
import com.utils.Executor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public final class PriceRangeFilter extends Component {
  private WebElement button;
  private WebElement priceLabel;

  private final int[] initialPriceRange;

  public PriceRangeFilter(WebDriver driver, WebElement root, int... initialPriceRange) {
    super(driver, root);
    this.initialPriceRange = initialPriceRange;
  }

  @Override
  protected void initiateElements() {
    button = findElement(By.xpath("//button[@type='submit' and text()='Filter']"));
    priceLabel = findElement(By.className("price_label"));
  }

  WebElement minHandle() {
    return findElement(By.cssSelector(".price_slider span.ui-slider-handle:nth-of-type(1)"));
  }

  WebElement maxHandle() {
    return findElement(By.cssSelector(".price_slider span.ui-slider-handle:nth-of-type(2)"));
  }

  public String getPriceLabel() {
    return priceLabel.getText();
  }

  void ensurePriceLabelExistence(int min, int max) {
    String label = FormatUtils.f("Price: ${} — ${}", min, max);
    Executor.raiseIf(() -> Executor.hasEvaluatedAndSucceed(() ->
        waitFor(ExpectedConditions.textToBePresentInElement(priceLabel, label), 10)),
      FormatUtils.f("Expected price filter label ❝{}❞", label));
  }

  public boolean hasPriceLabel(int min, int max) {
    return Executor.hasEvaluatedAndSucceed(() -> ensurePriceLabelExistence(min, max));
  }

  public void setRange(double min, double max) {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript(
      "jQuery('input#min_price').val(arguments[0]);" +
        "jQuery('input#max_price').val(arguments[1]);" +
        "jQuery('body').trigger('price_slider_slide', [arguments[0], arguments[1]]);" +
        "jQuery('body').trigger('price_slider_updated', [arguments[0], arguments[1]]);",
      min, max
    );
  }

  public Page applyFilter(String pageName) {
    button.click();
    return ProductsPage.buildFor(pageName, getDriver());
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    ensureExistenceOfElement(By.xpath("//h2[text()='Filter by price']"));
    ensureExistenceOfElement(By.cssSelector("div.price_slider"));
    ensureExistenceOfElement(minHandle());
    ensureExistenceOfElement(maxHandle());
    waitForElementToBeInteractive(button, 5);
    ensurePriceLabelExistence(initialPriceRange[0], initialPriceRange[1]);
  }
}
