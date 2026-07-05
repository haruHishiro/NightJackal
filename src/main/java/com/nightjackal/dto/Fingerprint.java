package com.nightjackal.dto;

import java.util.List;
import java.util.Map;

public class Fingerprint {
    private String server;          // nginx/1.18.0, Apache/2.4.54
    private String framework;       // Spring Boot, Django, Laravel, Express
    private String language;        // Java, PHP, Python, Node.js, Ruby
    private String cms;             // WordPress, Joomla, Drupal
    private String database;        // PostgreSQL, MySQL, MongoDB
    private Map<String, String> headers;
    private List<String> cookies;   // JSESSIONID, PHPSESSID, session
    private List<String> technologies;
}
