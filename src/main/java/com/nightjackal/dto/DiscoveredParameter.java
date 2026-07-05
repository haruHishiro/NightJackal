package com.nightjackal.dto;

import java.util.Objects;

public class DiscoveredParameter {
    private String name;
    private String source;          // "url", "form", "json", "header", "cookie"
    private String httpMethod;      // GET, POST, PUT
    private String exampleValue;    // Пример значения (для анализа)
    private boolean isSensitive;    // password, csrf_token, etc.
    private boolean isLoginField;   // Если это поле логина/пароля
    private String formAction;      // Если параметр из формы
    private String inputType;       // text, password, hidden, email, file
    private boolean isRequired;

    public DiscoveredParameter(String name, String source, String httpMethod, String exampleValue, boolean isSensitive,
                               boolean isLoginField, String formAction, String inputType, boolean isRequired) {
        this.name = name;
        this.source = source;
        this.httpMethod = httpMethod;
        this.exampleValue = exampleValue;
        this.isSensitive = isSensitive;
        this.isLoginField = isLoginField;
        this.formAction = formAction;
        this.inputType = inputType;
        this.isRequired = isRequired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscoveredParameter that = (DiscoveredParameter) o;
        return isSensitive == that.isSensitive && isLoginField == that.isLoginField && isRequired == that.isRequired
                && Objects.equals(name, that.name) && Objects.equals(source, that.source) && Objects.equals(httpMethod, that.httpMethod)
                && Objects.equals(exampleValue, that.exampleValue) && Objects.equals(formAction, that.formAction) && Objects.equals(inputType, that.inputType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, source, httpMethod, exampleValue, isSensitive, isLoginField, formAction, inputType, isRequired);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(String exampleValue) {
        this.exampleValue = exampleValue;
    }

    public boolean isSensitive() {
        return isSensitive;
    }

    public void setSensitive(boolean sensitive) {
        isSensitive = sensitive;
    }

    public boolean isLoginField() {
        return isLoginField;
    }

    public void setLoginField(boolean loginField) {
        isLoginField = loginField;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }
}
