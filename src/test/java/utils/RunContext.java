package utils;

import pages.BasePage;

import java.util.List;

public final class RunContext {
  private RunContext() {}

  public static BasePage currentPage;
  public static String currentPageName;
  public static String searchKeyword;
  public static List<String> productNameList;

  public static void clear() {
    currentPage = null;
    currentPageName = null;
    searchKeyword = null;
    productNameList = null;
  }
}
