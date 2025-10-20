package com.pages;

import com.pages.concerns.Waits;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public abstract class Page extends Waits {
  @Getter
  private final WebDriver driver;
}
