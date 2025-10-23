package com.pages.product.components;

import com.pages.Page;
import com.pages.product.SearchResultsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class SearchByName extends Component {
  private WebElement field;
  private WebElement button;

  public SearchByName(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  protected void initiateElements() {
    field = findElement(By.id("woocommerce-product-search-field-0"));
    button = findElement(By.xpath("//button[@type='submit' and text()='Search']"));
  }

  public void enterKeyword(String keyword) {
    field.sendKeys(keyword);
  }

  public Page clickSearchButton(String keyword) {
    button.click();
    return new SearchResultsPage(keyword, getDriver());
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForElementToBeInteractive(field, 5);
    waitForVisibility(button, 3);
  }
}
