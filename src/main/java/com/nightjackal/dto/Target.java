package com.nightjackal.dto;

import java.util.Map;

public class Target {
    private String url;
    private String parameter;       // Основной параметр для инъекции (если не найден автоопределением)
    private String method;          // GET, POST, PUT, DELETE
    private String authToken;       // Bearer-токен (если есть)
    private Map<String, String> headers;
    private String bodyTemplate;    // Для POST-запросов с JSON/XML ({{payload}} — место вставки)
    private String contentType;
}
