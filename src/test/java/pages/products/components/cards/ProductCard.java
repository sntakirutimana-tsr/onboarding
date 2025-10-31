package pages.products.components.cards;

import pages.products.components.Component;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static utils.FormatUtils.*;
import static utils.Executor.*;

public abstract class ProductCard extends Component {
  @FindBy(tagName = "img")
  private WebElement img;
  @FindBy(css = ".onsale")
  private WebElement saleBadge;
  @FindBy(css = ".woocommerce-Price-amount.amount bdi")
  private List<WebElement> priceTags;
  @FindBy(css = ".star-rating > span")
  private WebElement rating;
  @FindBy(xpath = "//a[text()='Add to cart']")
  private WebElement addToCartButton;

  public ProductCard(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  boolean isOnSale() {
    return hasEvaluatedAndSucceed(saleBadge::isDisplayed) || priceTags.size() == 2;
  }

  boolean hasPrice() {
    if (isOnSale())
      return getPrice(0) > getPrice(1);
    return priceTags.size() == 1 && getText(priceTags.getFirst()).matches("^\\$\\d+(\\.\\d{2})?$");
  }

  boolean hasCategory(String expectedCategory) {
    List<String> actualCategories = extractFormattedCategories(getRoot().getAttribute("class"));
    return Arrays.stream(expectedCategory.split(","))
      .map(String::trim)
      .map(String::toLowerCase)
      .anyMatch(actualCategories::contains);
  }

  public double getRating() {
    String value = rating.getAttribute("style");
    return Double.parseDouble(Objects.requireNonNull(value).replaceAll("\\D", ""));
  }

  public String getName() {
    return getText(getTitle());
  }

  protected abstract WebElement getTitle();

  double getPrice(int tagIndex) {
    String value = getText(priceTags.get(tagIndex));
    return extractPrice(value);
  }

  public double getPrice() {
    return getPrice(isOnSale() ? 1 : 0);
  }

  @Override
  public void ensureIsReady() {
    raiseIf(img::isDisplayed);
    raiseIf(() -> getTitle().isDisplayed() && !getName().isBlank());
    raiseIf(() -> getRating() >= 0 && getRating() <= 100);
    raiseIf(this::hasPrice, "Product must have one or two price tags");
  }

  public void ensureIsReady(String category) {
    ensureIsReady();
    raiseIf(() -> hasCategory(category));
    raiseIf(() -> addToCartButton.isDisplayed(), "Must have the ❝ADD TO CARD❞ button");
  }
}
