package com.nightjackal.payload;

import com.nightjackal.dto.Payload;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Загрузчик пейлоадов из JSON-файлов.
 * Файлы должны лежать в resources/payloads/{type}.json
 */
public class PayloadLoader {

    private static final Logger logger = LoggerFactory.getLogger(PayloadLoader.class);
    private static final String BASE_PATH = "/payloads/";
    private final Gson gson = new Gson();

    /**
     * Загружает пейлоады по типу.
     *
     * @param type тип пейлоадов (traversal, xxe, sqli, command, xss, jwt, ssrf)
     * @return список пейлоадов
     * @throws RuntimeException если файл не найден или не может быть прочитан
     */
    public List<Payload> loadByType(String type) {
        String fileName = BASE_PATH + type + ".json";
        logger.debug("Loading payloads from: {}", fileName);

        try (var reader = new InputStreamReader(
                getClass().getResourceAsStream(fileName),
                StandardCharsets.UTF_8
        )) {
            if (getClass().getResourceAsStream(fileName) == null) {
                throw new RuntimeException("Payload file not found: " + fileName);
            }

            Type listType = new TypeToken<List<Payload>>() {}.getType();
            List<Payload> payloads = gson.fromJson(reader, listType);

            if (payloads == null) {
                payloads = new ArrayList<>();
            }

            logger.info("Loaded {} payloads of type '{}'", payloads.size(), type);
            return payloads;

        } catch (Exception e) {
            String msg = "Failed to load payloads from " + fileName;
            logger.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    /**
     * Загружает пейлоады и фильтрует по тегу.
     *
     * @param type тип пейлоадов
     * @param tag  тег для фильтрации (например, "time_based", "error_based")
     * @return список пейлоадов с указанным тегом
     */
    public List<Payload> loadByTypeAndTag(String type, String tag) {
        List<Payload> all = loadByType(type);
        if (tag == null || tag.isEmpty()) {
            return all;
        }

        return all.stream()
                .filter(p -> p.getTags() != null && p.getTags().contains(tag))
                .toList();
    }
}