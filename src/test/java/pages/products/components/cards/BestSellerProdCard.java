package pages.products.components.cards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class BestSellerProdCard extends ProductCard {
  @FindBy(css = "span.product-title")
  private WebElement title;

  public BestSellerProdCard(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  protected WebElement getTitle() {
    return title;
  }
}
