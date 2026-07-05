package com.nightjackal.dto;

import java.util.List;
import java.util.Objects;

public class ChainStep {
    private String name;
    private String description;
    private boolean success;
    private Object result;          // Найденный файл, креды, сессия, и т.д.
    private List<ChainStep> subSteps;
    private String error;
    private long executionTime;

    public ChainStep(String name, String description, boolean success, Object result, List<ChainStep> subSteps, String error, long executionTime) {
        this.name = name;
        this.description = description;
        this.success = success;
        this.result = result;
        this.subSteps = subSteps;
        this.error = error;
        this.executionTime = executionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChainStep chainStep = (ChainStep) o;
        return success == chainStep.success && executionTime == chainStep.executionTime && Objects.equals(name, chainStep.name)
                && Objects.equals(description, chainStep.description) && Objects.equals(result, chainStep.result)
                && Objects.equals(subSteps, chainStep.subSteps) && Objects.equals(error, chainStep.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, success, result, subSteps, error, executionTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public List<ChainStep> getSubSteps() {
        return subSteps;
    }

    public void setSubSteps(List<ChainStep> subSteps) {
        this.subSteps = subSteps;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}
