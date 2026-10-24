package com.admiralcoder.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public final class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By items = By.cssSelector("[data-testid='item']");
    private final By message = By.cssSelector("[data-testid='message']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
    }

    public String heading() {
        return driver.findElement(By.tagName("h1")).getText();
    }

    public List<WebElement> items() {
        return driver.findElements(items);
    }

    public void expectCount(int count) {
        wait.until(d -> d.findElements(items).size() == count);
    }

    public void search(String term) {
        driver.findElement(By.cssSelector("[data-testid='search']")).sendKeys(term);
    }

    public void add(String name, String quantity) {
        driver.findElement(By.cssSelector("[data-testid='name']")).sendKeys(name);
        driver.findElement(By.cssSelector("[data-testid='quantity']")).sendKeys(quantity);
        driver.findElement(By.cssSelector("[data-testid='add']")).click();
    }

    public void delete(String name) {
        WebElement row = wait.until(d -> d.findElements(items).stream()
                .filter(item -> item.getText().contains(name)).findFirst().orElse(null));
        row.findElement(By.cssSelector("[data-testid='delete']")).click();
    }

    public String feedback() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }

    public boolean emptyStateVisible() {
        return driver.findElement(By.cssSelector("[data-testid='empty']")).isDisplayed();
    }

    public void reload() {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
    }
}
