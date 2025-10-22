package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class DriverProvider {
  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  public static void remove() {
    driver.remove();
  }

  private static WebDriver create() {
    log.info("⏳ Reading driver browser to initialize from CLI arguments..");
    String browser = System.getProperty("browser", "chrome");

    log.info("⏳ Initiate selenium webdriver for browser~{}..", browser);
    WebDriver webDriver = switch (browser.toLowerCase()) {
      case "firefox" -> firefoxDriver();
      case "edge" -> edgeDriver();
      case "chrome" -> chromedriver();
      default -> throw new RuntimeException("Unknown browser~" + browser);
    };
    log.info("✅ {} webdriver initiated successfully", browser);
    webDriver.manage().window().maximize();
    driver.set(webDriver);
    return webDriver;
  }

  public static WebDriver get() {
    WebDriver webDriver = driver.get();

    if (Objects.nonNull(webDriver)) return webDriver;

    log.info("\uD83D\uDEAB No driver found for current thread, creating a new one..⏳");
    return create();
  }

  private static WebDriver chromedriver() {
    // Default browser options
    ChromeOptions options = new ChromeOptions();

    options.addArguments(
      "--user-data-dir=/tmp/chrome-profile-" +
        Thread.currentThread().getId() + "-" +
        System.currentTimeMillis()
    );
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-extensions");
    options.addArguments("--disable-gpu");
    options.addArguments("--disable-web-security");
    options.addArguments("--disable-features=VizDisplayCompositor");
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--port=" + getAvailablePort());

    // Setup browser driver automatically
    WebDriverManager.chromedriver().setup();

    // Initiate an instance of the browser driver
    return new ChromeDriver(options);
  }

  private static WebDriver firefoxDriver() {
    // Default browser options
    FirefoxOptions options = new FirefoxOptions();
    FirefoxProfile profile = new FirefoxProfile();

    profile.setPreference("browser.privatebrowsing.autostart", true);
    options.setProfile(profile);

    // Setup browser driver automatically
    WebDriverManager.firefoxdriver().setup();

    // Initiate an instance of the browser driver
    return new FirefoxDriver(options);
  }

  private static WebDriver edgeDriver() {
    // Default browser options
    EdgeOptions options = new EdgeOptions();

    options.addArguments(
      "--user-data-dir=/tmp/edge-profile-" +
        Thread.currentThread().getId() + "-" +
        System.currentTimeMillis()
    );
    // Add isolation options
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-extensions");
    options.addArguments("--disable-gpu");
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--port=" + getAvailablePort());

    // Setup browser driver automatically
    WebDriverManager.edgedriver().setup();

    // Initiate an instance of the browser driver
    try {
      return new EdgeDriver(options);
    } catch (Exception e) {
      log.error("Failed to create Edge driver: {}", e.getMessage());
      throw new RuntimeException("Edge driver initialization failed", e);
    }
  }

  private static int getAvailablePort() {
    try (ServerSocket socket = new ServerSocket(0)) {
      return socket.getLocalPort();
    } catch (IOException e) {
      return 9515 + (int) (Math.random() * 1000);
    }
  }
}
