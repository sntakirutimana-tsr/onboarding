package pages.products.components.cards;

import pages.products.components.Component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static utils.Formatters.*;
import static utils.Executor.*;

public abstract class ProductCard extends Component {
  private final By imgLocator = By.tagName("img");
  private final By onsaleLocator = By.className("onsale");
  private final By priceLocator = By.cssSelector(".woocommerce-Price-amount.amount bdi");
  private final By ratingLocator = By.cssSelector(".star-rating > span");
  private final By addToCartLocator = By.xpath("//a[text()='Add to cart']");

  public ProductCard(WebDriver webDriver, WebElement root) {
    super(webDriver, null, root);
  }

  List<WebElement> priceTags() {
    return findAllBy(priceLocator);
  }

  boolean isOnSale() {
    return priceTags().size() == 2 ||
      hasEvaluatedSuccessfully(() -> getRootElement().findElement(onsaleLocator).isDisplayed());
  }

  boolean hasPrice() {
    if (isOnSale())
      return getPrice(0) > getPrice(1);
    return priceTags().size() == 1 && priceTags().getFirst().getText().matches("^\\$\\d+(\\.\\d{2})?$");
  }

  boolean hasCategory(String expectedCategory) {
    List<String> actualCategories = extractFormattedCategories(getRootElement().getAttribute("class"));
    return Arrays.stream(expectedCategory.split(","))
      .map(String::trim)
      .map(String::toLowerCase)
      .anyMatch(actualCategories::contains);
  }

  public double getRating() {
    String value = findBy(ratingLocator).getAttribute("style");
    return Double.parseDouble(Objects.requireNonNull(value).replaceAll("\\D", ""));
  }

  public String getName() {
    return getText(title());
  }

  protected abstract By title();

  double getPrice(int tagIndex) {
    String value = priceTags().get(tagIndex).getText();
    return extractPrice(value);
  }

  public double getPrice() {
    return getPrice(isOnSale() ? 1 : 0);
  }

  public void hasAllNecessaryDetails() {
    raiseIf(findBy(imgLocator)::isDisplayed);
    raiseIf(() -> findBy(title()).isDisplayed() && !getName().isBlank());
    raiseIf(() -> {
      double rate = getRating();
      return rate >= 0 && rate <= 100;
    });
    raiseIf(this::hasPrice, "Product must have one or two price tags");
  }

  public void hasAllNecessaryDetails(String category) {
    hasAllNecessaryDetails();
    raiseIf(() -> hasCategory(category));
    raiseIf(findBy(addToCartLocator)::isDisplayed, "Must have the ❝ADD TO CARD❞ button");
  }
}
