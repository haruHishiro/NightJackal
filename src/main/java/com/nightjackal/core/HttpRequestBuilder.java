package com.nightjackal.core;

import com.nightjackal.dto.Payload;
import com.nightjackal.dto.Target;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Строит HTTP-запросы с подстановкой пейлоадов в нужное место.
 * Поддерживает:
 * - GET: подстановка в URL-параметр
 * - POST/PUT: подстановка в тело запроса (JSON, XML, form-data)
 * - Заголовки: подстановка в значение заголовка
 */
public class HttpRequestBuilder {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestBuilder.class);

    /**
     * Строит GET-запрос с пейлоадом в URL-параметре.
     */
    public Request buildGetRequest(Target target, Payload payload) {
        String urlWithPayload = buildUrlWithPayload(target.getUrl(), target.getParameter(), payload);
        logger.debug("Built GET URL: {}", urlWithPayload);

        Request.Builder builder = new Request.Builder()
                .url(urlWithPayload)
                .get();

        addHeaders(builder, target.getHeaders());
        return builder.build();
    }

    /**
     * Строит POST-запрос с пейлоадом в теле.
     */
    public Request buildPostRequest(Target target, Payload payload) {
        return buildBodyRequest(target, payload, "POST");
    }

    /**
     * Строит PUT-запрос с пейлоадом в теле.
     */
    public Request buildPutRequest(Target target, Payload payload) {
        return buildBodyRequest(target, payload, "PUT");
    }

    /**
     * Строит DELETE-запрос.
     */
    public Request buildDeleteRequest(Target target, Payload payload) {
        String urlWithPayload = buildUrlWithPayload(target.getUrl(), target.getParameter(), payload);
        logger.debug("Built DELETE URL: {}", urlWithPayload);

        Request.Builder builder = new Request.Builder()
                .url(urlWithPayload)
                .delete();

        addHeaders(builder, target.getHeaders());
        return builder.build();
    }

    /**
     * Универсальный метод для POST/PUT с телом.
     */
    private Request buildBodyRequest(Target target, Payload payload, String method) {
        String body = buildBodyWithPayload(target.getBodyTemplate(), payload);
        if (body == null) {
            body = "";
        }

        MediaType mediaType = detectMediaType(target.getContentType());
        RequestBody requestBody = RequestBody.create(body, mediaType);

        logger.debug("Built {} body: {}", method, body);

        Request.Builder builder = new Request.Builder()
                .url(target.getUrl())
                .method(method, requestBody);

        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("Content-Type", "application/json; charset=utf-8");
        defaultHeaders.put("User-Agent", "NightJackal/1.0");

        if (target.getHeaders() == null || target.getHeaders().isEmpty()) {
            for (Map.Entry<String, String> entry : defaultHeaders.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        } else {
            addHeaders(builder, target.getHeaders());
        }

        return builder.build();
    }

    /**
     * Подставляет пейлоад в URL-параметр.
     * OkHttp автоматически кодирует параметры, поэтому не кодируем вручную.
     */
    private String buildUrlWithPayload(String baseUrl, String parameterName, Payload payload) {
        String payloadValue = payload.getValue();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        if (urlBuilder == null) {
            throw new IllegalArgumentException("Invalid URL: " + baseUrl);
        }

        if (parameterName == null || parameterName.isEmpty()) {
            urlBuilder.addQueryParameter("payload", payloadValue);
        } else {
            urlBuilder.setQueryParameter(parameterName, payloadValue);
        }

        return urlBuilder.build().toString();
    }

    /**
     * Подставляет пейлоад в тело запроса.
     */
    private String buildBodyWithPayload(String bodyTemplate, Payload payload) {
        String payloadValue = payload.getValue();

        if (bodyTemplate == null || bodyTemplate.isEmpty()) {
            return payloadValue;
        }

        return bodyTemplate.replace("{{payload}}", payloadValue);
    }

    /**
     * Определяет MediaType по Content-Type.
     */
    private MediaType detectMediaType(String contentType) {
        if (contentType == null || contentType.isEmpty()) {
            return MediaType.parse("application/json; charset=utf-8");
        }
        MediaType parsed = MediaType.parse(contentType);
        return parsed != null ? parsed : MediaType.parse("application/json; charset=utf-8");
    }

    /**
     * Добавляет заголовки в билдер.
     */
    private void addHeaders(Request.Builder builder, Map<String, String> headers) {
        if (headers == null) return;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
    }
}