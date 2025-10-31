package pages.products.components.cards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegularProdCard extends ProductCard {
  @FindBy(css = "h2.woocommerce-loop-product__title")
  private WebElement title;

  public RegularProdCard(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  protected WebElement getTitle() {
    return title;
  }
}
