package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

  private final By productName = By.cssSelector("td.product-name a");
  private final By productPrice = By.cssSelector("td.product-price .woocommerce-Price-amount.amount");
  private final By productQuantity = By.cssSelector("td.product-quantity .qty");
  private final By productSubtotal = By.cssSelector("td.product-subtotal .woocommerce-Price-amount.amount");

  public CartPage(WebDriver driver) {
    super(driver);
  }

  public String getProductName() {
    return getText(findBy(productName));
  }

  public String getProductPrice() {
    return getText(findBy(productPrice));
  }

  public String getProductQuantity() {
    return getAttribute(findBy(productQuantity), "value");
  }

  public String getProductSubtotal() {
    return getText(findBy(productSubtotal));
  }
}
