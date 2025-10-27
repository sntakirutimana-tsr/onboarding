package com.pages.product.components;

import com.pages.Page;
import com.pages.product.ProductsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class SubCategoryFilter extends Component {
  private WebElement dropdown;

  public SubCategoryFilter(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  protected void initiateElements() {
    dropdown = findElement(By.id("product_cat"));
  }

  public Page selectOption(String subCategory) {
    select(dropdown, subCategory);
    return ProductsPage.buildFor(subCategory.replace("’", "'"), getDriver());
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForVisibility(findElement(By.xpath("//h2[text()='Browse By Categories']")), 5);
    waitForElementToBeInteractive(dropdown, 5);
  }
}
