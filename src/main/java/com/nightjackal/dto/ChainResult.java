package com.nightjackal.dto;

import java.util.List;
import java.util.Objects;

public class ChainResult {
    private String targetUrl;
    private boolean isCompromised;
    private List<ChainStep> steps;
    private String finalAccess;     // URL админки, сессия, шелл
    private String summary;         // Краткое описание цепочки
    private long totalTime;

    public ChainResult(String targetUrl, boolean isCompromised, List<ChainStep> steps, String finalAccess, String summary, long totalTime) {
        this.targetUrl = targetUrl;
        this.isCompromised = isCompromised;
        this.steps = steps;
        this.finalAccess = finalAccess;
        this.summary = summary;
        this.totalTime = totalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChainResult that = (ChainResult) o;
        return isCompromised == that.isCompromised && totalTime == that.totalTime && Objects.equals(targetUrl, that.targetUrl)
                && Objects.equals(steps, that.steps) && Objects.equals(finalAccess, that.finalAccess) && Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetUrl, isCompromised, steps, finalAccess, summary, totalTime);
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public boolean isCompromised() {
        return isCompromised;
    }

    public void setCompromised(boolean compromised) {
        isCompromised = compromised;
    }

    public List<ChainStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ChainStep> steps) {
        this.steps = steps;
    }

    public String getFinalAccess() {
        return finalAccess;
    }

    public void setFinalAccess(String finalAccess) {
        this.finalAccess = finalAccess;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }
}
