package pages;

import pages.products.ProductPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class Homepage extends BasePage {
  private final By navMenuStoreLocator = By.id("menu-item-1227");
  private final By navMenuMenLocator = By.id("menu-item-1228");
  private final By navMenuWomenLocator = By.id("menu-item-1229");
  private final By navMenuAccessoriesLocator = By.id("menu-item-1230");

  public Homepage(WebDriver driver) {
    super(driver);
  }

  public ProductPage browseProducts(String pageName) {
    String page = pageName.replaceAll("'s$", "").toLowerCase();
    switch (page) {
      case "men" -> click(navMenuMenLocator);
      case "women" -> click(navMenuWomenLocator);
      case "accessories" -> click(navMenuAccessoriesLocator);
      case "store" -> click(navMenuStoreLocator);
      default -> throw new IllegalArgumentException("Unknown nav menu option~" + pageName);
    }
    return new ProductPage(getDriver());
  }
}
