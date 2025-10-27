package com.pages.product.components;

import com.pages.Page;
import com.pages.product.ProductsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SortBy extends Component {
  public SortBy(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForElementToBeInteractive(getRoot(), 5);
  }

  public String getSelectedText() {
    return new Select(getRoot()).getFirstSelectedOption().getText();
  }

  public Page selectOption(String criterion, String pageName) {
    select(getRoot(), criterion);
    return ProductsPage.buildFor(pageName, getDriver());
  }
}
