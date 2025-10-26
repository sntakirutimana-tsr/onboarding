package com.pages.product.components.cards;

import com.pages.product.components.Component;
import com.utils.Executor;
import com.utils.FormatUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.utils.FormatUtils.extractFormattedCategories;

public abstract class ProductCard extends Component {
  protected WebElement name;

  public ProductCard(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  List<WebElement> priceTags() {
    return findElements(By.cssSelector(".woocommerce-Price-amount.amount bdi"));
  }

  WebElement rateWidth() {
    return findElement(By.cssSelector(".star-rating > span"));
  }

  boolean isOnSale() {
    return Executor.hasEvaluatedAndSucceed(() -> ensureExistenceOfElement(By.cssSelector(".onsale"))) || priceTags().size() == 2;
  }

  boolean hasName() {
    return name.isDisplayed() && !getText(name).isBlank();
  }

  boolean hasRating() {
    double rating = getRating();
    return rateWidth() != null && rating >= 0 && rating <= 100;
  }

  boolean hasPrice() {
    if (isOnSale())
      return getPrice(0) > getPrice(1);
    return priceTags().size() == 1 && getText(priceTags().get(0)).matches("^\\$\\d+(\\.\\d{2})?$");
  }

  boolean hasCategory(String expectedCategory) {
    List<String> actualCategories = extractFormattedCategories(getRoot().getAttribute("class"));
    return Arrays.stream(expectedCategory.split(","))
      .map(String::trim)
      .map(String::toLowerCase)
      .anyMatch(actualCategories::contains);
  }

  public double getRating() {
    String value = rateWidth().getAttribute("style");
    return Double.parseDouble(Objects.requireNonNull(value).replaceAll("\\D", ""));
  }

  public String getName() {
    return getText(name);
  }

  public double getPrice(int tagIndex) {
    String value = getText(priceTags().get(tagIndex));
    return FormatUtils.extractPrice(value);
  }

  public double getPrice() {
    return getPrice(isOnSale() ? 1 : 0);
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    ensureExistenceOfElement(By.cssSelector("img"));
    Executor.raiseIf(this::hasName);
    Executor.raiseIf(this::hasRating);
    Executor.raiseIf(this::hasPrice, "Product must have one or two price tags");
  }

  public void ensureAllCheckpointsAreReady(String category) {
    ensureAllCheckpointsAreReady();
    Executor.raiseIf(() -> hasCategory(category));
    Executor.raiseIf(() ->
        findElement(By.xpath("//a[text()='Add to cart']")).isDisplayed(),
      "Must have the ❝ADD TO CARD❞ button");
  }
}
