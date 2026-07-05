package com.nightjackal.dto;

public class CheckResult {
    private String checkName;
    private String targetUrl;
    private String parameter;
    private String payload;
    private boolean vulnerable;
    private boolean isLoginForm;
    private String bypassType;      // sqli, nosqli, log4j, etc.
    private String details;         // Детали: какой маркер найден, какой статус
    private int statusCode;
    private double responseTime;
    private String evidence;
}
