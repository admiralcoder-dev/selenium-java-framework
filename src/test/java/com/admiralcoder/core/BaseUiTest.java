package com.admiralcoder.core;

import com.admiralcoder.pages.InventoryPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

@ExtendWith(ScreenshotOnFailure.class)
public abstract class BaseUiTest {
    private static DemoServer server;
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    protected InventoryPage inventory;

    @BeforeAll
    static void startDemo() throws IOException {
        server = new DemoServer();
    }

    @BeforeEach
    void startBrowser() {
        WebDriver driver = DriverFactory.create();
        DRIVER.set(driver);
        driver.get(server.url());
        inventory = new InventoryPage(driver);
    }

    static WebDriver activeDriver() {
        return DRIVER.get();
    }

    @AfterEach
    void stopBrowser() {
        WebDriver driver = DRIVER.get();
        DRIVER.remove();
        if (driver != null) driver.quit();
    }

    @AfterAll
    static void stopDemo() {
        if (server != null) server.close();
    }
}
