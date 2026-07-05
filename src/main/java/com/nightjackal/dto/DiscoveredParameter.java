package com.nightjackal.dto;

public class DiscoveredParameter {
    private String name;
    private String source;          // "url", "form", "json", "header", "cookie"
    private String httpMethod;      // GET, POST, PUT
    private String exampleValue;    // Пример значения (для анализа)
    private boolean isSensitive;    // password, csrf_token, etc.
    private boolean isLoginField;   // Если это поле логина/пароля
    private String formAction;      // Если параметр из формы
    private String inputType;       // text, password, hidden, email, file
    private boolean isRequired;
}
