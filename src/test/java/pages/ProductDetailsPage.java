package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage{


  private final By addToCartButton = By.name("add-to-cart");
  private final By successMessage = By.cssSelector(".woocommerce-message");
  private final By viewCartLink = By.cssSelector(".woocommerce-message .wc-forward");


  public ProductDetailsPage(WebDriver driver) {
    super(driver);
  }


  public void clickAddToCart() {
    waitFor(ExpectedConditions.elementToBeClickable(addToCartButton));
    click(addToCartButton);
  }

  public String getSuccessMessageText() {
    waitFor(ExpectedConditions.visibilityOfElementLocated(successMessage));
    return getText(findBy(successMessage));
  }

  public void clickViewCartLink() {
    click(viewCartLink);
  }

}