package com.nightjackal.core;

import com.nightjackal.dto.ResponseData;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientTest.class);
    private final HttpClient client = new HttpClient();

    @Test
    void testGetRequest() {
        ResponseData response = client.get("https://httpbin.org/get");

        assertNotNull(response, "ResponseData is null");
        assertEquals(200, response.getStatusCode(),
                "Expected 200, got " + response.getStatusCode() + ". Error: " + response.getError());
        assertTrue(response.getBody().contains("\"url\": \"https://httpbin.org/get\""),
                "Body does not contain expected URL. Body: " + response.getBody());
        assertTrue(response.getResponseTime() > 0,
                "Response time should be > 0, got " + response.getResponseTime());

        logger.info("GET test passed. Status: {}, Time: {}s",
                response.getStatusCode(), response.getResponseTime());
    }

    @Test
    void testGetWithHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Custom-Header", "NightJackal");

        ResponseData response = client.get("https://httpbin.org/headers", headers);

        assertNotNull(response, "ResponseData is null");
        assertEquals(200, response.getStatusCode(),
                "Expected 200, got " + response.getStatusCode() + ". Error: " + response.getError());
        assertTrue(response.getBody().contains("X-Custom-Header"),
                "Header not found in response. Body: " + response.getBody());

        logger.info("GET with headers test passed.");
    }

    @Test
    void testPostJson() {
        String json = "{\"name\": \"NightJackal\", \"type\": \"scanner\"}";

        ResponseData response = client.postJson("https://httpbin.org/post", json);

        assertNotNull(response, "ResponseData is null");
        assertEquals(200, response.getStatusCode(),
                "Expected 200, got " + response.getStatusCode() + ". Error: " + response.getError());
        assertTrue(response.getBody().contains("\"name\": \"NightJackal\""),
                "Body does not contain 'name'. Body: " + response.getBody());
        assertTrue(response.getBody().contains("\"type\": \"scanner\""),
                "Body does not contain 'type'. Body: " + response.getBody());

        logger.info("POST JSON test passed.");
    }

    @Test
    void testPostForm() {
        Map<String, String> form = new HashMap<>();
        form.put("username", "nightjackal");
        form.put("password", "test123");

        ResponseData response = client.postForm("https://httpbin.org/post", form);

        assertNotNull(response, "ResponseData is null");
        assertEquals(200, response.getStatusCode(),
                "Expected 200, got " + response.getStatusCode() + ". Error: " + response.getError());
        assertTrue(response.getBody().contains("\"username\": \"nightjackal\""),
                "Body does not contain username. Body: " + response.getBody());
        assertTrue(response.getBody().contains("\"password\": \"test123\""),
                "Body does not contain password. Body: " + response.getBody());

        logger.info("POST Form test passed.");
    }

    @Test
    void testTimeout() {
        HttpClient slowClient = new HttpClient(1000, false);
        ResponseData response = slowClient.get("https://httpbin.org/delay/5");

        assertNotNull(response, "ResponseData is null");
        assertEquals(0, response.getStatusCode(),
                "Expected 0 (timeout), got " + response.getStatusCode());
        assertTrue(response.getError() != null &&
                        response.getError().toLowerCase().contains("timeout"),
                "Expected timeout error, got: " + response.getError());

        logger.info("Timeout test passed. Error: {}", response.getError());
    }

    @Test
    void testNotFound() {
        ResponseData response = client.get("https://httpbin.org/status/404");

        assertNotNull(response, "ResponseData is null");
        assertEquals(404, response.getStatusCode(),
                "Expected 404, got " + response.getStatusCode() + ". Error: " + response.getError());

        logger.info("404 test passed.");
    }

    @Test
    void testRedirectsDisabled() {
        HttpClient noRedirectClient = new HttpClient(10000, false);
        ResponseData response = noRedirectClient.get("https://httpbin.org/redirect/1");

        assertNotNull(response, "ResponseData is null");
        assertEquals(302, response.getStatusCode(),
                "Expected 302, got " + response.getStatusCode() + ". Error: " + response.getError());

        logger.info("Redirects disabled test passed. Status: {}", response.getStatusCode());
    }

    @Test
    void testRedirectsEnabled() {
        HttpClient slowClient = new HttpClient(60000, true);
        ResponseData response = slowClient.get("https://httpbin.org/redirect/1");

        assertNotNull(response);
        assertEquals(200, response.getStatusCode(),
                "Expected 200, got " + response.getStatusCode() + ". Error: " + response.getError());

        logger.info("Redirects enabled test passed. Status: {}", response.getStatusCode());
    }

    @Test
    void testPutRequest() {
        String json = "{\"updated\": true}";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        ResponseData response = client.put("https://httpbin.org/put", json, headers);

        assertNotNull(response, "ResponseData is null");
        assertEquals(200, response.getStatusCode(),
                "Expected 200, got " + response.getStatusCode() + ". Error: " + response.getError());
        assertTrue(response.getBody().contains("\"updated\": true"),
                "Body does not contain 'updated'. Body: " + response.getBody());

        logger.info("PUT test passed.");
    }

    @Test
    void testDeleteRequest() {
        HttpClient slowClient = new HttpClient(30000, false);

        Map<String, String> headers = new HashMap<>();
        headers.put("X-Custom", "delete-test");

        ResponseData response = slowClient.delete("https://httpbin.org/delete", headers);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode(),
                "Expected 200, got " + response.getStatusCode() + ". Error: " + response.getError());
        assertTrue(response.getBody().contains("\"X-Custom\": \"delete-test\""),
                "Header not found in response. Body: " + response.getBody());

        logger.info("DELETE test passed.");
    }
}
