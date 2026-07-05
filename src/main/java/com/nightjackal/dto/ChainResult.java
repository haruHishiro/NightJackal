package com.nightjackal.dto;

import java.util.List;

public class ChainResult {
    private String targetUrl;
    private boolean isCompromised;
    private List<ChainStep> steps;
    private String finalAccess;     // URL админки, сессия, шелл
    private String summary;         // Краткое описание цепочки
    private long totalTime;
}
