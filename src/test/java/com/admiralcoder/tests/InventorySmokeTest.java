package com.admiralcoder.tests;

import com.admiralcoder.core.BaseUiTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventorySmokeTest extends BaseUiTest {
    @Test
    void pageLoadsWithExpectedHeading() {
        assertEquals("Inventory Demo", inventory.heading());
    }

    @Test
    void seededItemsAreVisible() {
        inventory.expectCount(2);
        assertTrue(inventory.items().stream().anyMatch(item -> item.getText().contains("Rice")));
        assertTrue(inventory.items().stream().anyMatch(item -> item.getText().contains("Milk")));
    }
}
