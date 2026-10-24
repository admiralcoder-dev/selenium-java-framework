package com.admiralcoder.core;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ScreenshotOnFailure implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        WebDriver driver = BaseUiTest.activeDriver();
        if (driver instanceof TakesScreenshot screenshot) {
            try {
                Path directory = Path.of("target", "screenshots");
                Files.createDirectories(directory);
                String name = (context.getRequiredTestClass().getSimpleName() + "-" +
                        context.getRequiredTestMethod().getName()).replaceAll("[^a-zA-Z0-9_-]", "_");
                Files.write(directory.resolve(name + ".png"), screenshot.getScreenshotAs(OutputType.BYTES));
            } catch (Exception error) {
                System.err.println("Could not save failure screenshot: " + error.getMessage());
            }
        }
        throw throwable;
    }
}
