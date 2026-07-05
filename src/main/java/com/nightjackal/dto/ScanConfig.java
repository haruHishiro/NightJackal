package com.nightjackal.dto;

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
}
