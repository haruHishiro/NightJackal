package com.nightjackal.dto;

import java.util.Objects;

public class OpenPort {
    private int port;
    private String protocol;        // TCP, UDP
    private String service;         // ssh, http, mysql, redis, etc.
    private String banner;          // Баннер сервиса
    private String version;         // Версия из баннера
    private boolean isOpen;

    public OpenPort(int port, String protocol, String service, String banner, String version, boolean isOpen) {
        this.port = port;
        this.protocol = protocol;
        this.service = service;
        this.banner = banner;
        this.version = version;
        this.isOpen = isOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenPort openPort = (OpenPort) o;
        return port == openPort.port && isOpen == openPort.isOpen && Objects.equals(protocol, openPort.protocol)
                && Objects.equals(service, openPort.service) && Objects.equals(banner, openPort.banner) && Objects.equals(version, openPort.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, protocol, service, banner, version, isOpen);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
