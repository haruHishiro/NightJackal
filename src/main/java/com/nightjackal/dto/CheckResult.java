package com.nightjackal.dto;

import java.util.Objects;

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

    public CheckResult(String checkName, String targetUrl, String parameter, String payload, boolean vulnerable,
                       boolean isLoginForm, String bypassType, String details, int statusCode, double responseTime, String evidence) {
        this.checkName = checkName;
        this.targetUrl = targetUrl;
        this.parameter = parameter;
        this.payload = payload;
        this.vulnerable = vulnerable;
        this.isLoginForm = isLoginForm;
        this.bypassType = bypassType;
        this.details = details;
        this.statusCode = statusCode;
        this.responseTime = responseTime;
        this.evidence = evidence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckResult that = (CheckResult) o;
        return vulnerable == that.vulnerable && isLoginForm == that.isLoginForm && statusCode == that.statusCode
                && Double.compare(responseTime, that.responseTime) == 0 && Objects.equals(checkName, that.checkName)
                && Objects.equals(targetUrl, that.targetUrl) && Objects.equals(parameter, that.parameter) && Objects.equals(payload, that.payload)
                && Objects.equals(bypassType, that.bypassType) && Objects.equals(details, that.details) && Objects.equals(evidence, that.evidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkName, targetUrl, parameter, payload, vulnerable, isLoginForm, bypassType, details, statusCode, responseTime, evidence);
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public boolean isVulnerable() {
        return vulnerable;
    }

    public void setVulnerable(boolean vulnerable) {
        this.vulnerable = vulnerable;
    }

    public boolean isLoginForm() {
        return isLoginForm;
    }

    public void setLoginForm(boolean loginForm) {
        isLoginForm = loginForm;
    }

    public String getBypassType() {
        return bypassType;
    }

    public void setBypassType(String bypassType) {
        this.bypassType = bypassType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }
}
