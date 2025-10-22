package com.pages.product.components;

import com.pages.product.components.cards.BestSellerProdCard;
import com.pages.product.components.cards.ProductCard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public final class OurBestSellers extends Component {
  @FindBy(css = ".product_list_widget li")
  private List<WebElement> productCardList;

  public OurBestSellers(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForVisibility(findElement(By.xpath("//h2[text()='Our Best Sellers']")), 5);
    waitFor(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".product_list_widget li"), 3), 5);
    productCardList.stream()
      .map(r -> new BestSellerProdCard(getDriver(), r))
      .forEach(ProductCard::ensureAllCheckpointsAreReady);
  }
}
