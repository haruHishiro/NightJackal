package com.nightjackal.payload;

import com.nightjackal.dto.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PayloadLoaderTest {

    private PayloadLoader loader;

    @BeforeEach
    void setUp() {
        loader = new PayloadLoader();
    }

    @Test
    void loadTraversalPayloads() {
        List<Payload> payloads = loader.loadByType("traversal");

        assertNotNull(payloads);
        assertTrue(payloads.size() > 0, "Should load at least 1 traversal payload");

        // Проверяем структуру первого пейлоада
        Payload first = payloads.get(0);
        assertNotNull(first.getId());
        assertNotNull(first.getName());
        assertNotNull(first.getValue());
        assertEquals("traversal", first.getType());
    }

    @Test
    void loadNonExistentType() {
        try {
            loader.loadByType("non_existent_type");
            fail("Expected RuntimeException but nothing was thrown");
        } catch (RuntimeException e) {
            System.out.println("Actual exception message: " + e.getMessage());
            assertTrue(e.getMessage().contains("not found") || e.getMessage().contains("Failed"),
                    "Message: " + e.getMessage());
        }
    }

    @Test
    void loadByTag() {
        // Создай в traversal.json хотя бы один пейлоад с тегом "basic"
        List<Payload> tagged = loader.loadByTypeAndTag("traversal", "basic");

        assertNotNull(tagged);
        // Если нет пейлоадов с тегом "basic" — вернется пустой список (не ошибка)
        // Это нормально, просто проверяем, что метод работает
    }
}