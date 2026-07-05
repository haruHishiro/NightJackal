package com.nightjackal.dto;

import java.util.List;

public class Payload {
    private String id;
    private String name;
    private String value;           // Сама строка пейлоада
    private String type;            // traversal, xxe, sqli, command, xss, jwt, ssrf
    private String description;
    private String encoding;        // url, base64, none
    private List<String> tags;
}
