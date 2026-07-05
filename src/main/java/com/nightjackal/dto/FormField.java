package com.nightjackal.dto;

import java.util.Objects;

public class FormField {
    private String name;
    private String type;            // text, password, hidden, email, file, checkbox, radio
    private String value;
    private String placeholder;
    private boolean isRequired;
    private boolean isDisabled;

    public FormField(String name, String type, String value, String placeholder, boolean isRequired, boolean isDisabled) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.placeholder = placeholder;
        this.isRequired = isRequired;
        this.isDisabled = isDisabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormField formField = (FormField) o;
        return isRequired == formField.isRequired && isDisabled == formField.isDisabled && Objects.equals(name, formField.name)
                && Objects.equals(type, formField.type) && Objects.equals(value, formField.value) && Objects.equals(placeholder, formField.placeholder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, value, placeholder, isRequired, isDisabled);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
