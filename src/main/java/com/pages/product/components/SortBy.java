package com.pages.product.components;

import com.pages.Page;
import com.pages.product.ProductsPage;

import com.utils.DriverProvider;
import com.utils.LoggerFactoryUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

public final class SortBy extends Component {
  private static final Logger log = LoggerFactoryUtil.getLogger(SortBy.class);

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
