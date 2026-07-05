package com.nightjackal.dto;

import java.util.Map;
import java.util.Objects;

public class Target {
    private String url;
    private String parameter;       // Основной параметр для инъекции (если не найден автоопределением)
    private String method;          // GET, POST, PUT, DELETE
    private String authToken;       // Bearer-токен (если есть)
    private Map<String, String> headers;
    private String bodyTemplate;    // Для POST-запросов с JSON/XML ({{payload}} — место вставки)
    private String contentType;

    public Target(String url, String parameter, String method, String authToken, Map<String, String> headers, String bodyTemplate, String contentType) {
        this.url = url;
        this.parameter = parameter;
        this.method = method;
        this.authToken = authToken;
        this.headers = headers;
        this.bodyTemplate = bodyTemplate;
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return Objects.equals(url, target.url) && Objects.equals(parameter, target.parameter) && Objects.equals(method, target.method) && Objects.equals(authToken, target.authToken) && Objects.equals(headers, target.headers) && Objects.equals(bodyTemplate, target.bodyTemplate) && Objects.equals(contentType, target.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, parameter, method, authToken, headers, bodyTemplate, contentType);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBodyTemplate() {
        return bodyTemplate;
    }

    public void setBodyTemplate(String bodyTemplate) {
        this.bodyTemplate = bodyTemplate;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
