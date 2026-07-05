package com.nightjackal.dto;

import java.util.List;

public class ChainStep {
    private String name;
    private String description;
    private boolean success;
    private Object result;          // Найденный файл, креды, сессия, и т.д.
    private List<ChainStep> subSteps;
    private String error;
    private long executionTime;
}
