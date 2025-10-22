package com.pages.product.components;

import com.pages.product.components.cards.BestSellerProdCard;
import com.pages.product.components.cards.ProductCard;
import com.utils.Executor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public final class OurBestSellers extends Component {
  private List<WebElement> productCardList;

  public OurBestSellers(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  protected void initiateElements() {
    productCardList = findElements(By.cssSelector(".product_list_widget li"));
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    hasElement(By.xpath("//h2[text()='Our Best Sellers']"));
    Executor.raiseIf(() -> productCardList.size() == 3, "Our Best Sellers must have three products");
    productCardList.stream()
      .map(r -> new BestSellerProdCard(getDriver(), r))
      .forEach(ProductCard::ensureAllCheckpointsAreReady);
  }
}
