package com.nightjackal.dto;

public class OpenPort {
    private int port;
    private String protocol;        // TCP, UDP
    private String service;         // ssh, http, mysql, redis, etc.
    private String banner;          // Баннер сервиса
    private String version;         // Версия из баннера
    private boolean isOpen;
}
