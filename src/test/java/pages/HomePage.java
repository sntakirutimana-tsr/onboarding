package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
  private final By viewCartLink = By.cssSelector("a[title='View cart']");
  private final By cartCount = By.cssSelector(".ast-cart-menu-wrap .count");

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public void AddToCart(String productName) {
    By addToCartButton = By.xpath("//a[contains(@aria-label, '" + productName + "')]");
    waitFor(ExpectedConditions.elementToBeClickable(addToCartButton));
    click(addToCartButton);
  }

  public boolean isViewCartLinkVisible() {
    waitFor(ExpectedConditions.visibilityOfElementLocated(viewCartLink));
    return findBy(viewCartLink).isDisplayed();
  }

  public int getCartCount() {
    waitFor(ExpectedConditions.visibilityOfElementLocated(cartCount));
    String text = findBy(cartCount).getText();
    return Integer.parseInt(text);
  }

  public void clickViewCartLink() {
    click(viewCartLink);
  }

}
