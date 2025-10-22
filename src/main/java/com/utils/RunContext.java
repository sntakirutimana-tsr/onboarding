package com.utils;

import com.pages.Page;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class RunContext {
  private static final ThreadLocal<Page> page = new ThreadLocal<>();

  public static <P extends Page> void setPage(P currentPage) {
    page.set(currentPage);
  }

  public static Page getPage() {
    return page.get();
  }
}
