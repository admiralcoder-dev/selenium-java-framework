package com.admiralcoder.tests;

import com.admiralcoder.core.BaseUiTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryWorkflowTest extends BaseUiTest {
    @Test
    void searchFiltersInventory() {
        inventory.search("Rice");
        inventory.expectCount(1);
        assertTrue(inventory.items().getFirst().getText().contains("Rice"));
    }

    @Test
    void unknownSearchShowsEmptyState() {
        inventory.search("NotPresent");
        inventory.expectCount(0);
        assertTrue(inventory.emptyStateVisible());
    }

    @Test
    void addItemUpdatesList() {
        inventory.add("Apples", "4");
        inventory.expectCount(3);
        assertTrue(inventory.items().stream().anyMatch(item -> item.getText().contains("Apples")));
        assertEquals("Item added", inventory.feedback());
    }

    @Test
    void deleteItemUpdatesList() {
        inventory.delete("Rice");
        inventory.expectCount(1);
        assertFalse(inventory.items().stream().anyMatch(item -> item.getText().contains("Rice")));
        assertEquals("Item deleted", inventory.feedback());
    }

    @Test
    void invalidQuantityIsRejected() {
        inventory.add("Invalid", "-1");
        assertTrue(inventory.feedback().contains("valid item"));
        inventory.expectCount(2);
    }

    @Test
    void missingNameIsRejected() {
        inventory.add("", "2");
        assertTrue(inventory.feedback().contains("valid item"));
        inventory.expectCount(2);
    }

    @Test
    void addedItemPersistsAfterReload() {
        inventory.add("Pasta", "3");
        inventory.expectCount(3);
        inventory.reload();
        inventory.expectCount(3);
        assertTrue(inventory.items().stream().anyMatch(item -> item.getText().contains("Pasta")));
    }
}
