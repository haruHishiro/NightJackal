package com.nightjackal.dto;

import java.util.List;
import java.util.Map;

public class ResponseData {
    private int statusCode;
    private long contentLength;
    private double responseTime;    // в секундах
    private String body;            // первые N символов (для анализа)
    private Map<String, String> headers;
    private String url;             // Финальный URL (после редиректов)
    private String error;           // Если произошла ошибка
    private boolean isTimeout;
    private List<String> redirectChain;
}
