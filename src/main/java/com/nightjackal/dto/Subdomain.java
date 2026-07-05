package com.nightjackal.dto;

import java.util.Objects;

public class Subdomain {
    private String domain;          // admin.target.com
    private String fullUrl;         // https://admin.target.com
    private int statusCode;
    private String ipAddress;
    private boolean isAlive;
    private String cname;           // CNAME-запись, если есть

    public Subdomain(String domain, String fullUrl, int statusCode, String ipAddress, boolean isAlive, String cname) {
        this.domain = domain;
        this.fullUrl = fullUrl;
        this.statusCode = statusCode;
        this.ipAddress = ipAddress;
        this.isAlive = isAlive;
        this.cname = cname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subdomain subdomain = (Subdomain) o;
        return statusCode == subdomain.statusCode && isAlive == subdomain.isAlive && Objects.equals(domain, subdomain.domain)
                && Objects.equals(fullUrl, subdomain.fullUrl) && Objects.equals(ipAddress, subdomain.ipAddress)
                && Objects.equals(cname, subdomain.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, fullUrl, statusCode, ipAddress, isAlive, cname);
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
