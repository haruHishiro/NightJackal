package com.nightjackal.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DiscoveredEndpoint {
    private String path;
    private String method;          // GET, POST, PUT, DELETE, OPTIONS
    private int statusCode;
    private List<String> parameters;
    private String contentType;
    private int contentLength;
    private Map<String, String> headers;
    private boolean isLoginEndpoint;

    public DiscoveredEndpoint(String path, String method, int statusCode, List<String> parameters,
                              String contentType, int contentLength, Map<String, String> headers, boolean isLoginEndpoint) {
        this.path = path;
        this.method = method;
        this.statusCode = statusCode;
        this.parameters = parameters;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.headers = headers;
        this.isLoginEndpoint = isLoginEndpoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscoveredEndpoint that = (DiscoveredEndpoint) o;
        return statusCode == that.statusCode && contentLength == that.contentLength && isLoginEndpoint == that.isLoginEndpoint
                && Objects.equals(path, that.path) && Objects.equals(method, that.method) && Objects.equals(parameters, that.parameters)
                && Objects.equals(contentType, that.contentType) && Objects.equals(headers, that.headers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, method, statusCode, parameters, contentType, contentLength, headers, isLoginEndpoint);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean isLoginEndpoint() {
        return isLoginEndpoint;
    }

    public void setLoginEndpoint(boolean loginEndpoint) {
        isLoginEndpoint = loginEndpoint;
    }
}
