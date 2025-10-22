package com.pages.product.components.cards;

import com.pages.product.components.Component;
import com.utils.FormatUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ProductCard extends Component {
  private final String PRICE_REGEX = "^\\$\\d+(\\.\\d{2})?$";

  protected WebElement name;
  private List<WebElement> priceTags;
  private WebElement rating;
  private WebElement rateWidth;
  protected WebElement categoryTag;
  protected WebElement addToCartButton;

  public ProductCard(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  protected void initiateElements() {
    priceTags = findElements(By.cssSelector(".woocommerce-Price-amount.amount bdi"));
    rating = findElement(By.cssSelector(".star-rating strong.rating"));
    rateWidth = findElement(By.cssSelector(".star-rating > span"));
    categoryTag = findElement(By.cssSelector("span.ast-woo-product-category"));
    addToCartButton = findElement(By.xpath("//button[@type='submit' and text()='Add to cart']"));
  }

  boolean isOnSale() {
    return findElement(By.className("onsale")).isDisplayed() || priceTags.size() == 2;
  }

  boolean hasName() {
    return name.isDisplayed() && !getText(name).isBlank();
  }

  boolean hasRating() {
    return rating.isDisplayed() && rateWidth.isDisplayed() && getRatePercentage() == getRating() * 20.0;
  }

  boolean hasPrice() {
    return (isOnSale() || priceTags.size() == 1) &&
      priceTags.stream()
        .allMatch(p -> getText(p).matches(PRICE_REGEX));
  }

  boolean hasCategory(String category) {
    return categoryTag.isDisplayed() && !getText(categoryTag).isBlank();
  }

  double getPrice(int tagIndex) {
    String value = getText(priceTags.get(tagIndex));
    return FormatUtils.extractPrice(value);
  }

  public String getCategory() {
    return getText(categoryTag);
  }

  public double getOriginalPrice() {
    return getPrice(0);
  }

  public double getDiscountedPrice() {
    return getPrice(1);
  }

  public int getRating() {
    String value = getText(rating).trim();
    return Integer.parseInt(value);
  }

  public double getRatePercentage() {
    String value = rateWidth.getCssValue("width");
    return Double.parseDouble(value.replaceAll("\\D", ""));
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    hasElement(By.cssSelector("img"));
    assert hasName();
    assert hasRating();
    assert hasPrice() : "Product must have one or two price tags";
  }

  public void ensureAllCheckpointsAreReady(String category) {
    ensureAllCheckpointsAreReady();
    assert hasCategory(category);
    assert addToCartButton.isDisplayed() : "Must have the `ADD TO CARD button";
  }
}
