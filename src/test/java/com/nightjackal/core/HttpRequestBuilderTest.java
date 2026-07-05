package com.nightjackal.core;

import com.nightjackal.dto.Payload;
import com.nightjackal.dto.Target;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestBuilderTest {

    private HttpRequestBuilder builder;
    private Payload traversalPayload;
    private Payload sqliPayload;

    @BeforeEach
    void setUp() {
        builder = new HttpRequestBuilder();

        traversalPayload = new Payload();
        traversalPayload.setId("traversal_basic");
        traversalPayload.setName("Basic Path Traversal");
        traversalPayload.setValue("../../../etc/passwd");
        traversalPayload.setType("traversal");
        traversalPayload.setEncoding("none");

        sqliPayload = new Payload();
        sqliPayload.setId("sqli_basic");
        sqliPayload.setName("SQL Injection");
        sqliPayload.setValue("' OR '1'='1");
        sqliPayload.setType("sqli");
        sqliPayload.setEncoding("url");
    }

    @Test
    void buildGetRequest() {
        Target target = new Target();
        target.setUrl("https://httpbin.org/get");
        target.setParameter("file");

        Request request = builder.buildGetRequest(target, traversalPayload);

        assertEquals("GET", request.method());
        HttpUrl url = request.url();
        assertEquals("../../../etc/passwd", url.queryParameter("file"));
    }

    @Test
    void buildGetRequestWithEncodedPayload() {
        Target target = new Target();
        target.setUrl("https://httpbin.org/get");
        target.setParameter("q");

        Request request = builder.buildGetRequest(target, sqliPayload);

        assertEquals("GET", request.method());
        String url = request.url().toString();
        assertTrue(url.contains("%27%20OR%20%271%27%3D%271"),
                "URL should contain encoded payload, but was: " + url);
    }

    @Test
    void buildPostJsonRequest() {
        Target target = new Target();
        target.setUrl("https://httpbin.org/post");
        target.setMethod("POST");
        target.setContentType("application/json");
        target.setBodyTemplate("{\"username\": \"admin\", \"password\": \"{{payload}}\"}");

        Request request = builder.buildPostRequest(target, sqliPayload);

        assertEquals("POST", request.method());
        assertTrue(request.body() != null, "Request body should not be null");
        assertTrue(request.header("Content-Type") != null);
        assertTrue(request.header("Content-Type").contains("application/json"));
    }

    @Test
    void buildPostJsonRequestWithRawPayload() {
        Target target = new Target();
        target.setUrl("https://httpbin.org/post");
        target.setMethod("POST");
        target.setContentType("application/json");
        target.setBodyTemplate(null);

        Request request = builder.buildPostRequest(target, sqliPayload);

        assertEquals("POST", request.method());
        assertNotNull(request.body());
    }

    @Test
    void buildPostFormRequest() {
        Target target = new Target();
        target.setUrl("https://httpbin.org/post");
        target.setMethod("POST");
        target.setContentType("application/x-www-form-urlencoded");
        target.setBodyTemplate("username=admin&password={{payload}}");

        Request request = builder.buildPostRequest(target, sqliPayload);

        assertEquals("POST", request.method());
        assertNotNull(request.body());
    }

    @Test
    void buildPutRequest() {
        Target target = new Target();
        target.setUrl("https://httpbin.org/put");
        target.setMethod("PUT");
        target.setContentType("application/json");
        target.setBodyTemplate("{\"updated\": \"{{payload}}\"}");

        Request request = builder.buildPutRequest(target, traversalPayload);

        assertEquals("PUT", request.method());
        assertNotNull(request.body());
    }

    @Test
    void buildDeleteRequest() {
        Target target = new Target();
        target.setUrl("https://httpbin.org/delete");
        target.setParameter("id");

        Request request = builder.buildDeleteRequest(target, traversalPayload);

        assertEquals("DELETE", request.method());
        HttpUrl url = request.url();
        assertEquals("../../../etc/passwd", url.queryParameter("id"));
    }

    @Test
    void buildRequestWithHeaders() {
        Target target = new Target();
        target.setUrl("https://httpbin.org/get");
        target.setParameter("test");

        Map<String, String> headers = new HashMap<>();
        headers.put("X-Custom-Header", "NightJackal");
        headers.put("Authorization", "Bearer token123");
        target.setHeaders(headers);

        Request request = builder.buildGetRequest(target, traversalPayload);

        assertEquals("NightJackal", request.header("X-Custom-Header"));
        assertEquals("Bearer token123", request.header("Authorization"));
    }

    @Test
    void buildRequestWithMissingParameter() {
        Target target = new Target();
        target.setUrl("https://httpbin.org/get");
        target.setParameter(null);

        Request request = builder.buildGetRequest(target, traversalPayload);

        HttpUrl url = request.url();
        assertEquals("../../../etc/passwd", url.queryParameter("payload"));
    }

    @Test
    void buildRequestWithNullBodyTemplate() {
        Target target = new Target();
        target.setUrl("https://httpbin.org/post");
        target.setMethod("POST");
        target.setBodyTemplate(null);

        Request request = builder.buildPostRequest(target, sqliPayload);

        assertEquals("POST", request.method());
        assertNotNull(request.body());
    }
}