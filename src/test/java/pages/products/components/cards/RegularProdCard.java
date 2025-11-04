package pages.products.components.cards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegularProdCard extends ProductCard {

  public RegularProdCard(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  protected By title() {
    return By.cssSelector("h2.woocommerce-loop-product__title");
  }
}
