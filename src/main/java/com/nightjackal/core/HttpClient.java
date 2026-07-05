package com.nightjackal.core;

import com.nightjackal.dto.ResponseData;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * HTTP-клиент на основе OkHttp.
 * Отправляет запросы, обрабатывает таймауты и редиректы,
 * возвращает унифицированный ResponseData.
 */
public class HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private final OkHttpClient client;
    private final int timeout;
    private final boolean followRedirects;

    /**
     * Конструктор с настройками по умолчанию.
     */
    public HttpClient() {
        this(10000, false);
    }

    /**
     * Конструктор с пользовательскими настройками.
     *
     * @param timeout          таймаут в миллисекундах
     * @param followRedirects  следовать ли редиректам
     */
    public HttpClient(int timeout, boolean followRedirects) {
        this.timeout = timeout;
        this.followRedirects = followRedirects;

        this.client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .followRedirects(followRedirects)
                .followSslRedirects(followRedirects)
                .addInterceptor(new UserAgentInterceptor())
                .build();

        logger.debug("HttpClient initialized with timeout={}ms, followRedirects={}", timeout, followRedirects);
    }

    /**
     * GET-запрос.
     *
     * @param url полный URL
     * @return ResponseData
     */
    public ResponseData get(String url) {
        return get(url, new HashMap<>());
    }

    /**
     * GET-запрос с заголовками.
     *
     * @param url     полный URL
     * @param headers заголовки
     * @return ResponseData
     */
    public ResponseData get(String url, Map<String, String> headers) {
        Request.Builder builder = new Request.Builder().url(url).get();
        addHeaders(builder, headers);
        return execute(builder.build());
    }

    /**
     * POST-запрос с телом.
     *
     * @param url     полный URL
     * @param body    тело запроса
     * @param headers заголовки
     * @return ResponseData
     */
    public ResponseData post(String url, String body, Map<String, String> headers) {
        MediaType mediaType = detectMediaType(headers);
        RequestBody requestBody = RequestBody.create(body, mediaType);

        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(requestBody);
        addHeaders(builder, headers);
        return execute(builder.build());
    }

    /**
     * POST-запрос с form-urlencoded.
     *
     * @param url   полный URL
     * @param form  параметры формы
     * @return ResponseData
     */
    public ResponseData postForm(String url, Map<String, String> form) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : form.entrySet()) {
            formBuilder.add(entry.getKey(), entry.getValue());
        }

        Request request = new Request.Builder()
                .url(url)
                .post(formBuilder.build())
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        return execute(request);
    }

    /**
     * POST-запрос с JSON-телом.
     *
     * @param url  полный URL
     * @param json JSON-строка
     * @return ResponseData
     */
    public ResponseData postJson(String url, String json) {
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        return execute(request);
    }

    /**
     * PUT-запрос.
     *
     * @param url  полный URL
     * @param body тело запроса
     * @param headers заголовки
     * @return ResponseData
     */
    public ResponseData put(String url, String body, Map<String, String> headers) {
        MediaType mediaType = detectMediaType(headers);
        RequestBody requestBody = RequestBody.create(body, mediaType);

        Request.Builder builder = new Request.Builder()
                .url(url)
                .put(requestBody);
        addHeaders(builder, headers);
        return execute(builder.build());
    }

    /**
     * DELETE-запрос.
     *
     * @param url полный URL
     * @param headers заголовки
     * @return ResponseData
     */
    public ResponseData delete(String url, Map<String, String> headers) {
        Request.Builder builder = new Request.Builder().url(url).delete();
        addHeaders(builder, headers);
        return execute(builder.build());
    }

    /**
     * Базовый метод выполнения запроса.
     *
     * @param request OkHttp-запрос
     * @return ResponseData
     */
    private ResponseData execute(Request request) {
        long startTime = System.nanoTime();

        try (Response response = client.newCall(request).execute()) {
            long elapsedMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
            double elapsedSec = elapsedMs / 1000.0;

            String body = response.body() != null ? response.body().string() : "";
            int statusCode = response.code();

            // Собираем заголовки
            Map<String, String> headers = new HashMap<>();
            for (String name : response.headers().names()) {
                headers.put(name, response.headers().get(name));
            }

            // Собираем цепочку редиректов (если есть)
            List<String> redirectChain = response.priorResponse() != null ?
                    response.priorResponse().headers().values("Location") :
                    List.of();

            logger.debug("Request to {} completed in {}ms with status {}",
                    request.url(), elapsedMs, statusCode);

            return new ResponseData(
                    statusCode,
                    body.length(),
                    elapsedSec,
                    body.length() > 10000 ? body.substring(0, 10000) : body, // ограничиваем для памяти
                    headers,
                    request.url().toString(),
                    null,
                    false,
                    redirectChain
            );

        } catch (IOException e) {
            long elapsedMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
            logger.error("Request to {} failed: {}", request.url(), e.getMessage());

            return new ResponseData(
                    0,
                    0,
                    elapsedMs / 1000.0,
                    "",
                    new HashMap<>(),
                    request.url().toString(),
                    e.getMessage(),
                    e instanceof java.net.SocketTimeoutException,
                    List.of()
            );
        }
    }

    /**
     * Добавляет заголовки в билдер запроса.
     */
    private void addHeaders(Request.Builder builder, Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Определяет MediaType по заголовку Content-Type.
     */
    private MediaType detectMediaType(Map<String, String> headers) {
        String contentType = headers.getOrDefault("Content-Type", "text/plain");
        return MediaType.parse(contentType);
    }

    /**
     * Интерсептор для подмены User-Agent.
     */
    private static class UserAgentInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request modified = original.newBuilder()
                    .header("User-Agent", "NightJackal/1.0 (Security Scanner)")
                    .build();
            return chain.proceed(modified);
        }
    }
}
