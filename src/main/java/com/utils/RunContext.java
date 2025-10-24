package com.utils;

import com.pages.Page;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class RunContext {
  private static final ThreadLocal<Page> page = new ThreadLocal<>();
  private static final ThreadLocal<String> pageName = new ThreadLocal<>();
  private static final ThreadLocal<String> searchKeyword = new ThreadLocal<>();
  private static final ThreadLocal<List<String>> productNameList = new ThreadLocal<>();

  public static <P extends Page> void setPage(P value) {
    page.set(value);
  }

  public static Page getPage() {
    return page.get();
  }

  public static void setPageName(String name) {
    pageName.set(name);
  }

  public static String getPageName() {
    return pageName.get();
  }

  public static void setSearchKeyword(String keyword) {
    searchKeyword.set(keyword);
  }

  public static String getSearchKeyword() {
    return searchKeyword.get();
  }

  public static void setProductNameList(List<String> value) {
    productNameList.set(value);
  }

  public static List<String> getProductNameList() {
    return productNameList.get();
  }

  public static void clear() {
    page.remove();
    pageName.remove();
    searchKeyword.remove();
    productNameList.remove();
  }
}
