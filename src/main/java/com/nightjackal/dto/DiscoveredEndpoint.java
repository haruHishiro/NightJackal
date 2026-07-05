package com.nightjackal.dto;

import java.util.List;
import java.util.Map;

public class DiscoveredEndpoint {
    private String path;
    private String method;          // GET, POST, PUT, DELETE, OPTIONS
    private int statusCode;
    private List<String> parameters;
    private String contentType;
    private int contentLength;
    private Map<String, String> headers;
    private boolean isLoginEndpoint;
}
