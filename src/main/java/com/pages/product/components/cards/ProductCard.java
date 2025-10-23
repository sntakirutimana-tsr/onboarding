package com.pages.product.components.cards;

import com.pages.product.components.Component;
import com.utils.Executor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class ProductCard extends Component {
  private final String PRICE_REGEX = "^\\$\\d+(\\.\\d{2})?$";

  protected WebElement name;

  public ProductCard(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  WebElement addToCartButton() {
    return findElement(By.xpath("//button[@type='submit' and text()='Add to cart']"));
  }

  WebElement categoryTag() {
    return findElement(By.cssSelector("span.ast-woo-product-category"));
  }

  List<WebElement> priceTags() {
    return findElements(By.cssSelector(".woocommerce-Price-amount.amount bdi"));
  }

  WebElement rateWidth() {
    return findElement(By.cssSelector(".star-rating > span"));
  }

  boolean isOnSale() {
    return Executor.hasEvaluatedAndSucceed(() -> hasElement(By.cssSelector(".onsale"))) || priceTags().size() == 2;
  }

  boolean hasName() {
    return name.isDisplayed() && !getText(name).isBlank();
  }

  boolean hasRating() {
    double rating = getRatePercentage();
    return rateWidth() != null && rating >= 0 && rating <= 100;
  }

  boolean hasPrice() {
    return (isOnSale() || priceTags().size() == 1) &&
      priceTags().stream()
        .allMatch(p -> getText(p).matches(PRICE_REGEX));
  }

  boolean hasCategory(String expectedCategory) {
    String actualCategory = getText(categoryTag()).toLowerCase();
    return Arrays.stream(expectedCategory.replaceAll(" ", "").split(","))
      .anyMatch(c -> actualCategory.startsWith(c.toLowerCase()));
  }

  public double getRatePercentage() {
    String value = rateWidth().getAttribute("style");
    return Double.parseDouble(Objects.requireNonNull(value).replaceAll("\\D", ""));
  }

  public String getName() {
    return getText(name);
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    hasElement(By.cssSelector("img"));
    Executor.raiseIf(this::hasName);
    Executor.raiseIf(this::hasRating);
    Executor.raiseIf(this::hasPrice, "Product must have one or two price tags");
  }

  public void ensureAllCheckpointsAreReady(String category) {
    ensureAllCheckpointsAreReady();
    Executor.raiseIf(() -> hasCategory(category));
    Executor.raiseIf(() -> addToCartButton() != null, "Must have the ❝ADD TO CARD❞ button");
  }
}
