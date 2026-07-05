package com.nightjackal.dto;

import java.util.List;
import java.util.Objects;

public class Payload {
    private String id;
    private String name;
    private String value;           // Сама строка пейлоада
    private String type;            // traversal, xxe, sqli, command, xss, jwt, ssrf
    private String description;
    private String encoding;        // url, base64, none
    private List<String> tags;

    public Payload(String id, String name, String value, String type, String description, String encoding, List<String> tags) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.type = type;
        this.description = description;
        this.encoding = encoding;
        this.tags = tags;
    }

    public Payload() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payload payload = (Payload) o;
        return Objects.equals(id, payload.id) && Objects.equals(name, payload.name) && Objects.equals(value, payload.value)
                && Objects.equals(type, payload.type) && Objects.equals(description, payload.description)
                && Objects.equals(encoding, payload.encoding) && Objects.equals(tags, payload.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, type, description, encoding, tags);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
