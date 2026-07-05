package com.nightjackal.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public ResponseData(int statusCode, long contentLength, double responseTime, String body, Map<String, String> headers,
                        String url, String error, boolean isTimeout, List<String> redirectChain) {
        this.statusCode = statusCode;
        this.contentLength = contentLength;
        this.responseTime = responseTime;
        this.body = body;
        this.headers = headers;
        this.url = url;
        this.error = error;
        this.isTimeout = isTimeout;
        this.redirectChain = redirectChain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseData that = (ResponseData) o;
        return statusCode == that.statusCode && contentLength == that.contentLength && Double.compare(responseTime, that.responseTime) == 0
                && isTimeout == that.isTimeout && Objects.equals(body, that.body) && Objects.equals(headers, that.headers)
                && Objects.equals(url, that.url) && Objects.equals(error, that.error) && Objects.equals(redirectChain, that.redirectChain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, contentLength, responseTime, body, headers, url, error, isTimeout, redirectChain);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isTimeout() {
        return isTimeout;
    }

    public void setTimeout(boolean timeout) {
        isTimeout = timeout;
    }

    public List<String> getRedirectChain() {
        return redirectChain;
    }

    public void setRedirectChain(List<String> redirectChain) {
        this.redirectChain = redirectChain;
    }
}
