package pages;

import pages.products.ProductPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static utils.ExtendedHelpers.waitForVisibility;
import static utils.Executor.hasEvaluatedAndSucceed;

public class Homepage extends BasePage {
  @FindBy(id = "menu-item-1227")
  private WebElement storeOption;

  @FindBy(id = "menu-item-1228")
  private WebElement menOption;

  @FindBy(id = "menu-item-1229")
  private WebElement womenOption;

  @FindBy(id = "menu-item-1230")
  private WebElement accessoriesOption;

  private final By viewCartLink = By.cssSelector("a[title='View cart']");
  private final By cartCount = By.cssSelector(".ast-cart-menu-wrap .count");

  public Homepage(WebDriver driver) {
    super(driver);
  }

  public boolean isReady() {
    return hasEvaluatedAndSucceed(() -> {
      waitForVisibility(driver, storeOption, 5);
      waitForVisibility(driver, menOption, 2);
      waitForVisibility(driver, womenOption, 2);
      waitForVisibility(driver, accessoriesOption, 2);
    });
  }

  public ProductPage browseProducts(String pageName) {
    String page = pageName.replaceAll("'s$", "").toLowerCase();
    switch (page) {
      case "men" -> menOption.click();
      case "women" -> womenOption.click();
      case "accessories" -> accessoriesOption.click();
      case "store" -> storeOption.click();
      default -> throw new IllegalArgumentException("Unknown menu option: " + pageName);
    }
    return new ProductPage(driver);
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
