package com.nightjackal.dto;

public class Subdomain {
    private String domain;          // admin.target.com
    private String fullUrl;         // https://admin.target.com
    private int statusCode;
    private String ipAddress;
    private boolean isAlive;
    private String cname;           // CNAME-запись, если есть
}
