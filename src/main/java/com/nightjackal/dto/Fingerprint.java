package com.nightjackal.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Fingerprint {
    private String server;          // nginx/1.18.0, Apache/2.4.54
    private String framework;       // Spring Boot, Django, Laravel, Express
    private String language;        // Java, PHP, Python, Node.js, Ruby
    private String cms;             // WordPress, Joomla, Drupal
    private String database;        // PostgreSQL, MySQL, MongoDB
    private Map<String, String> headers;
    private List<String> cookies;   // JSESSIONID, PHPSESSID, session
    private List<String> technologies;

    public Fingerprint(String server, String framework, String language, String cms, String database,
                       Map<String, String> headers, List<String> cookies, List<String> technologies) {
        this.server = server;
        this.framework = framework;
        this.language = language;
        this.cms = cms;
        this.database = database;
        this.headers = headers;
        this.cookies = cookies;
        this.technologies = technologies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fingerprint that = (Fingerprint) o;
        return Objects.equals(server, that.server) && Objects.equals(framework, that.framework)
                && Objects.equals(language, that.language) && Objects.equals(cms, that.cms) && Objects.equals(database, that.database)
                && Objects.equals(headers, that.headers) && Objects.equals(cookies, that.cookies) && Objects.equals(technologies, that.technologies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, framework, language, cms, database, headers, cookies, technologies);
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCms() {
        return cms;
    }

    public void setCms(String cms) {
        this.cms = cms;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public List<String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }
}
