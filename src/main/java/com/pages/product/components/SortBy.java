package com.pages.product.components;

import com.pages.Page;
import com.pages.product.ProductsPage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SortBy extends Component {
  public SortBy(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  Select selector() {
    return new Select(getRoot());
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForElementToBeInteractive(getRoot(), 5);
  }

  public String getSelectedText() {
    return selector().getFirstSelectedOption().getText();
  }

  public Page select(String criterion, String pageName) {
    try {
      selector().selectByContainsVisibleText(criterion);
    } catch (NoSuchElementException e) {
      log.error("Unable to locate select option with text: {}", e.getMessage());
    }
    return ProductsPage.buildFor(pageName, getDriver());
  }
}
