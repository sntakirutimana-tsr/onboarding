package com.pages.concerns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface Waitable extends Drivable {
  /**
   * Waits for the visibility of an element located by the specified {@link By} locator within the given timeout period.
   * <p>
   * This method uses an explicit wait to pause execution until the element becomes visible on the page,
   * up to the specified timeout in seconds. If the element does not become visible within the timeout,
   * a {@link org.openqa.selenium.TimeoutException} will be thrown.
   * </p>
   *
   * @param locator       the {@link By} locator used to find the element
   * @param timeoutInSecs the maximum time to wait in seconds for the element to become visible
   * @return the {@link WebElement} once it becomes visible
   * @throws org.openqa.selenium.TimeoutException       if the element does not become visible within the timeout
   * @throws org.openqa.selenium.NoSuchElementException if the locator does not find any element in the DOM
   */
  WebElement waitForVisibility(By locator, int timeoutInSecs);

  /**
   * Waits for the visibility of an element specified {@link WebElement} proxy within the given timeout period.
   * <p>
   * This method uses an explicit wait to pause execution until the element becomes visible on the page,
   * up to the specified timeout in seconds. If the element does not become visible within the timeout,
   * a {@link org.openqa.selenium.TimeoutException} will be thrown.
   * </p>
   *
   * @param element       the {@link WebElement} element proxy used to find the element
   * @param timeoutInSecs the maximum time to wait in seconds for the element to become visible
   * @throws org.openqa.selenium.TimeoutException       if the element does not become visible within the timeout
   * @throws org.openqa.selenium.NoSuchElementException if the locator does not find any element in the DOM
   */
  void waitForVisibility(WebElement element, int timeoutInSecs);

  /**
   * Waits until the specified {@link WebElement} contains the expected text
   * within the given timeout period.
   * <p>
   * This is commonly used to wait for a dropdown selection or a dynamically updated
   * field to display the expected text.
   * </p>
   *
   * @param element       the {@link WebElement} to check for the expected text
   * @param expected      the expected text to be present in the element
   * @param timeoutInSecs the maximum time to wait in seconds for the expected text to appear
   * @throws org.openqa.selenium.TimeoutException if the expected text does not appear within the timeout
   */
  void waitForTextVisibility(WebElement element, String expected, int timeoutInSecs);
}
