package com.nightjackal.dto;

import java.util.Objects;

public class ScanConfig {
    // Web Checks
    private boolean pathTraversal;
    private boolean xxe;
    private boolean sqlInjection;
    private boolean commandInjection;
    private boolean xss;
    private boolean jwtKidInjection;
    private boolean ssrf;
    private boolean httpMethodFuzzing;

    // Infra Checks
    private boolean portScanning;
    private boolean subdomainEnumeration;
    private boolean fingerprinting;

    // File Checks
    private boolean sensitiveFileBruteforce;
    private boolean credentialStuffing;

    // Discovery
    private boolean discoveryEnabled;
    private int maxForms;
    private int maxLinks;
    private boolean parseJson;
    private boolean parseHeaders;
    private boolean parseCookies;

    // Chain
    private boolean chainEnabled;
    private int chainMaxDepth;

    // Core settings
    private int timeout;
    private int threads;
    private int timeBasedThreshold;
    private int portScanTimeout;
    private int dnsTimeout;
    private int connectionTimeout;

    public ScanConfig(boolean pathTraversal, boolean xxe, boolean sqlInjection, boolean commandInjection, boolean xss,
                      boolean jwtKidInjection, boolean ssrf, boolean httpMethodFuzzing, boolean portScanning,
                      boolean subdomainEnumeration, boolean fingerprinting, boolean sensitiveFileBruteforce,
                      boolean credentialStuffing, boolean discoveryEnabled, int maxForms, int maxLinks, boolean parseJson,
                      boolean parseHeaders, boolean parseCookies, boolean chainEnabled, int chainMaxDepth, int timeout,
                      int threads, int timeBasedThreshold, int portScanTimeout, int dnsTimeout, int connectionTimeout) {
        this.pathTraversal = pathTraversal;
        this.xxe = xxe;
        this.sqlInjection = sqlInjection;
        this.commandInjection = commandInjection;
        this.xss = xss;
        this.jwtKidInjection = jwtKidInjection;
        this.ssrf = ssrf;
        this.httpMethodFuzzing = httpMethodFuzzing;
        this.portScanning = portScanning;
        this.subdomainEnumeration = subdomainEnumeration;
        this.fingerprinting = fingerprinting;
        this.sensitiveFileBruteforce = sensitiveFileBruteforce;
        this.credentialStuffing = credentialStuffing;
        this.discoveryEnabled = discoveryEnabled;
        this.maxForms = maxForms;
        this.maxLinks = maxLinks;
        this.parseJson = parseJson;
        this.parseHeaders = parseHeaders;
        this.parseCookies = parseCookies;
        this.chainEnabled = chainEnabled;
        this.chainMaxDepth = chainMaxDepth;
        this.timeout = timeout;
        this.threads = threads;
        this.timeBasedThreshold = timeBasedThreshold;
        this.portScanTimeout = portScanTimeout;
        this.dnsTimeout = dnsTimeout;
        this.connectionTimeout = connectionTimeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScanConfig that = (ScanConfig) o;
        return pathTraversal == that.pathTraversal && xxe == that.xxe && sqlInjection == that.sqlInjection
                && commandInjection == that.commandInjection && xss == that.xss && jwtKidInjection == that.jwtKidInjection
                && ssrf == that.ssrf && httpMethodFuzzing == that.httpMethodFuzzing && portScanning == that.portScanning
                && subdomainEnumeration == that.subdomainEnumeration && fingerprinting == that.fingerprinting
                && sensitiveFileBruteforce == that.sensitiveFileBruteforce && credentialStuffing == that.credentialStuffing
                && discoveryEnabled == that.discoveryEnabled && maxForms == that.maxForms && maxLinks == that.maxLinks
                && parseJson == that.parseJson && parseHeaders == that.parseHeaders && parseCookies == that.parseCookies
                && chainEnabled == that.chainEnabled && chainMaxDepth == that.chainMaxDepth && timeout == that.timeout
                && threads == that.threads && timeBasedThreshold == that.timeBasedThreshold && portScanTimeout == that.portScanTimeout
                && dnsTimeout == that.dnsTimeout && connectionTimeout == that.connectionTimeout;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathTraversal, xxe, sqlInjection, commandInjection, xss, jwtKidInjection, ssrf,
                httpMethodFuzzing, portScanning, subdomainEnumeration, fingerprinting, sensitiveFileBruteforce,
                credentialStuffing, discoveryEnabled, maxForms, maxLinks, parseJson, parseHeaders, parseCookies,
                chainEnabled, chainMaxDepth, timeout, threads, timeBasedThreshold, portScanTimeout, dnsTimeout, connectionTimeout);
    }

    public boolean isPathTraversal() {
        return pathTraversal;
    }

    public void setPathTraversal(boolean pathTraversal) {
        this.pathTraversal = pathTraversal;
    }

    public boolean isXxe() {
        return xxe;
    }

    public void setXxe(boolean xxe) {
        this.xxe = xxe;
    }

    public boolean isSqlInjection() {
        return sqlInjection;
    }

    public void setSqlInjection(boolean sqlInjection) {
        this.sqlInjection = sqlInjection;
    }

    public boolean isCommandInjection() {
        return commandInjection;
    }

    public void setCommandInjection(boolean commandInjection) {
        this.commandInjection = commandInjection;
    }

    public boolean isXss() {
        return xss;
    }

    public void setXss(boolean xss) {
        this.xss = xss;
    }

    public boolean isJwtKidInjection() {
        return jwtKidInjection;
    }

    public void setJwtKidInjection(boolean jwtKidInjection) {
        this.jwtKidInjection = jwtKidInjection;
    }

    public boolean isSsrf() {
        return ssrf;
    }

    public void setSsrf(boolean ssrf) {
        this.ssrf = ssrf;
    }

    public boolean isHttpMethodFuzzing() {
        return httpMethodFuzzing;
    }

    public void setHttpMethodFuzzing(boolean httpMethodFuzzing) {
        this.httpMethodFuzzing = httpMethodFuzzing;
    }

    public boolean isPortScanning() {
        return portScanning;
    }

    public void setPortScanning(boolean portScanning) {
        this.portScanning = portScanning;
    }

    public boolean isSubdomainEnumeration() {
        return subdomainEnumeration;
    }

    public void setSubdomainEnumeration(boolean subdomainEnumeration) {
        this.subdomainEnumeration = subdomainEnumeration;
    }

    public boolean isFingerprinting() {
        return fingerprinting;
    }

    public void setFingerprinting(boolean fingerprinting) {
        this.fingerprinting = fingerprinting;
    }

    public boolean isSensitiveFileBruteforce() {
        return sensitiveFileBruteforce;
    }

    public void setSensitiveFileBruteforce(boolean sensitiveFileBruteforce) {
        this.sensitiveFileBruteforce = sensitiveFileBruteforce;
    }

    public boolean isCredentialStuffing() {
        return credentialStuffing;
    }

    public void setCredentialStuffing(boolean credentialStuffing) {
        this.credentialStuffing = credentialStuffing;
    }

    public boolean isDiscoveryEnabled() {
        return discoveryEnabled;
    }

    public void setDiscoveryEnabled(boolean discoveryEnabled) {
        this.discoveryEnabled = discoveryEnabled;
    }

    public int getMaxForms() {
        return maxForms;
    }

    public void setMaxForms(int maxForms) {
        this.maxForms = maxForms;
    }

    public int getMaxLinks() {
        return maxLinks;
    }

    public void setMaxLinks(int maxLinks) {
        this.maxLinks = maxLinks;
    }

    public boolean isParseJson() {
        return parseJson;
    }

    public void setParseJson(boolean parseJson) {
        this.parseJson = parseJson;
    }

    public boolean isParseHeaders() {
        return parseHeaders;
    }

    public void setParseHeaders(boolean parseHeaders) {
        this.parseHeaders = parseHeaders;
    }

    public boolean isParseCookies() {
        return parseCookies;
    }

    public void setParseCookies(boolean parseCookies) {
        this.parseCookies = parseCookies;
    }

    public boolean isChainEnabled() {
        return chainEnabled;
    }

    public void setChainEnabled(boolean chainEnabled) {
        this.chainEnabled = chainEnabled;
    }

    public int getChainMaxDepth() {
        return chainMaxDepth;
    }

    public void setChainMaxDepth(int chainMaxDepth) {
        this.chainMaxDepth = chainMaxDepth;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public int getTimeBasedThreshold() {
        return timeBasedThreshold;
    }

    public void setTimeBasedThreshold(int timeBasedThreshold) {
        this.timeBasedThreshold = timeBasedThreshold;
    }

    public int getPortScanTimeout() {
        return portScanTimeout;
    }

    public void setPortScanTimeout(int portScanTimeout) {
        this.portScanTimeout = portScanTimeout;
    }

    public int getDnsTimeout() {
        return dnsTimeout;
    }

    public void setDnsTimeout(int dnsTimeout) {
        this.dnsTimeout = dnsTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}
