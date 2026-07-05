# NightJackal

## 🏗️ Архитектура

### Общая схема

```

┌─────────────────────────────────────────────────────────────────────────────────────────┐
│                                     LAUNCHER                                            │
│  1. Загружает конфигурацию (scanner-config.json, targets.json)                          │
│  2. Создает CheckRegistry с активными проверками                                        │
│  3. Для каждого Target запускает ParameterDiscovery → CheckRegistry → ReportGenerator   │
└─────────────────────────────────────┬───────────────────────────────────────────────────┘
                                      │
                                      ▼
┌─────────────────────────────────────────────────────────────────────────────────────────┐
│                              PARAMETER DISCOVERY                                        │
│  Автоопределение точек входа:                                                           │
│  ┌────────────────────┐  ┌────────────────────┐  ┌────────────────────────────────┐     │
│  │ HtmlParameter      │  │ JsonParameter      │  │ LoginFormDetector              │     │
│  │ Extractor          │  │ Extractor          │  │ - определяет формы логина      │     │
│  │ - парсит формы     │  │ - извлекает ключи  │  │ - анализирует поля (password)  │     │
│  │ - извлекает ссылки │  │ - рекурсивный обход│  │ - проверяет action/кнопки      │     │
│  └────────────────────┘  └────────────────────┘  └────────────────────────────────┘     │
│                                      │                                                  │
│                                      ▼                                                  │
│                         List<DiscoveredParameter>                                       │
└─────────────────────────────────────┬───────────────────────────────────────────────────┘
                                      │
                                      ▼
┌─────────────────────────────────────────────────────────────────────────────────────────┐
│                               CHECK REGISTRY                                            │
│  Хранит активные проверки (инициализируется из ScanConfig):                             │
│                                                                                         │
│  ┌─────────────────────────────────┐  ┌─────────────────────────────────────────────┐   │
│  │         WEB CHECKS              │  │         INFRA CHECKS                        │   │
│  ├─────────────────────────────────┤  ├─────────────────────────────────────────────┤   │
│  │ PathTraversalCheck              │  │ PortScannerCheck                            │   │
│  │ XxeCheck                        │  │ SubdomainEnumerationCheck                   │   │
│  │ SqlInjectionCheck               │  │ FingerprintCheck                            │   │
│  │ CommandInjectionCheck           │  └─────────────────────────────────────────────┘   │
│  │ XssCheck                        │                                                    │
│  │ JwtKidInjectionCheck            │  ┌─────────────────────────────────────────────┐   │
│  │ SsrfCheck                       │  │         FILE CHECKS                         │   │
│  │ HttpMethodFuzzingCheck          │  ├─────────────────────────────────────────────┤   │
│  └─────────────────────────────────┘  │ SensitiveFileBruteforceCheck                │   │
│                                       │ CredentialStuffingCheck                     │   │
│                                       └─────────────────────────────────────────────┘   │
│                                                                                         │
│  Для каждого Target: registry.runAllChecks(target) → List<CheckResult>                  │
└─────────────────────────────────────┬───────────────────────────────────────────────────┘
                                      │
                                      ▼
┌─────────────────────────────────────────────────────────────────────────────────────────┐
│                              CHAIN EXECUTOR (опционально)                               │
│  Выстраивает цепочку атак:                                                              │
│  ┌────────────────────┐  ┌────────────────────┐  ┌────────────────────────────────┐     │
│  │ FindBackupStep     │→ │ ExtractCredentials │→ │ LoginStep → SessionGrabStep    │     │
│  │ (.env, .git, .sql) │  │ Step               │  │                                │     │
│  └────────────────────┘  └────────────────────┘  └────────────────────────────────┘     │
│                                      │                                                  │
│                                      ▼                                                  │
│                              ChainResult (полный путь компрометации)                    │
└─────────────────────────────────────┬───────────────────────────────────────────────────┘
                                      │
                                      ▼
┌─────────────────────────────────────────────────────────────────────────────────────────┐
│                              REPORT GENERATOR                                           │
│  ┌────────────────────┐  ┌────────────────────────────────────────────────────────┐     │
│  │ ConsoleReporter    │  │ JsonReporter                                           │     │
│  │ - цветной вывод    │  │ - экспорт в JSON-файл                                  │     │
│  │ - группировка по   │  │ - машинно-читаемый формат                              │     │
│  │   уязвимостям      │  │ - интеграция с другими системами                       │     │
│  └────────────────────┘  └────────────────────────────────────────────────────────┘     │
└─────────────────────────────────────────────────────────────────────────────────────────┘
```

=--

### Поток данных

```

JSON-файлы (конфиги, пейлоады, словари)
│
▼
ConfigLoader / PayloadLoader / DictionaryLoader
│
▼
DTO: ScanConfig, List<Target>, List<Payload>
│
▼
ParameterDiscovery → List<DiscoveredParameter>
│
▼
CheckRegistry → List<Check>
│
▼
Для каждого параметра: Check.execute(target)
│
├── HttpClient → ResponseData
│       │
│       ▼
├── ResponseAnalyzer (SignatureAnalyzer / TimeBasedAnalyzer)
│       │
│       ▼
└── CheckResult (vulnerable: true/false)
│
▼
ChainExecutor (если включен) → ChainResult
│
▼
ReportGenerator → Console / JSON

```

---

### Связи модулей

| Модуль | Зависит от | Предоставляет |
|--------|------------|---------------|
| **Launcher** | ConfigLoader, ParameterDiscovery, CheckRegistry, ChainExecutor, ReportGenerator | Точка входа, оркестрация |
| **ConfigLoader** | Gson, scanner-config.json, targets.json | ScanConfig, List\<Target\> |
| **ParameterDiscovery** | HttpClient, Jsoup, Gson | List\<DiscoveredParameter\> |
| **LoginFormDetector** | Jsoup | Boolean (isLoginForm), LoginFormInfo |
| **CheckRegistry** | ScanConfig, List\<Check\> | List\<CheckResult\> |
| **Check** (интерфейс) | Target, HttpClient, PayloadLoader | CheckResult |
| **HttpClient** | OkHttp, HttpRequestBuilder | ResponseData |
| **HttpRequestBuilder** | — | Request с подставленным пейлоадом |
| **PayloadLoader** | Gson, payloads/*.json | List\<Payload\> |
| **ResponseAnalyzer** (интерфейс) | ResponseData, ScanConfig | boolean (уязвимо/не уязвимо) |
| **SignatureAnalyzer** | ResponseData, маркеры | boolean |
| **TimeBasedAnalyzer** | ResponseData, порог задержки | boolean |
| **BannerAnalyzer** | ResponseData (порт) | String (service, версия) |
| **FingerprintAnalyzer** | ResponseData (HTTP) | Fingerprint (стек технологий) |
| **ChainExecutor** | HttpClient, FileDownloader | ChainResult |
| **ReportGenerator** (интерфейс) | List\<CheckResult\>, List\<ChainResult\> | Отчет в нужном формате |
| **ConsoleReporter** | List\<CheckResult\> | Цветной вывод в терминал |
| **JsonReporter** | List\<CheckResult\> | JSON-файл |

---

### Модули проверок

| Проверка | Тип | Метод детектирования |
|----------|-----|---------------------|
| **PathTraversalCheck** | Web | Сигнатурный анализ (`root:x:`, `[boot loader]`) |
| **XxeCheck** | Web | Извлечение файлов через внешние DTD |
| **SqlInjectionCheck** | Web | Error-based + Time-based (задержка) |
| **CommandInjectionCheck** | Web | Детекция вывода команд (`uid=`, `whoami`) |
| **XssCheck** | Web | Проверка возврата инъекции в HTML |
| **JwtKidInjectionCheck** | Web | Подстановка пути в `kid` + HMAC-подпись |
| **SsrfCheck** | Web | Доступ к internal-ресурсам (AWS, GCP, localhost) |
| **HttpMethodFuzzingCheck** | Web | Проверка разрешенных методов (PUT, DELETE, TRACE) |
| **PortScannerCheck** | Infra | Socket-сканирование + баннеры |
| **SubdomainEnumerationCheck** | Infra | DNS-запросы, проверка HTTP(S) доступности |
| **FingerprintCheck** | Infra | Анализ заголовков, cookies, путей |
| **SensitiveFileBruteforceCheck** | File | Поиск `.env`, `.git`, `.sql`, `composer.json` |
| **CredentialStuffingCheck** | File | Проверка слабых паролей в формах логина |

---

### Структура проекта

```

vuln-scanner-java/
├── src/main/java/com/vulnscanner/
│   ├── Launcher.java
│   │
│   ├── config/
│   │   ├── ConfigLoader.java
│   │   └── loader/
│   │       └── JsonConfigLoader.java
│   │
│   ├── dto/
│   │   ├── ScanConfig.java
│   │   ├── Target.java
│   │   ├── Payload.java
│   │   ├── ResponseData.java
│   │   ├── CheckResult.java
│   │   ├── DiscoveredEndpoint.java
│   │   ├── DiscoveredParameter.java
│   │   ├── FormField.java
│   │   ├── OpenPort.java
│   │   ├── Subdomain.java
│   │   ├── Fingerprint.java
│   │   └── ChainStep.java
│   │
│   ├── registry/
│   │   ├── CheckRegistry.java
│   │   └── Check.java
│   │
│   ├── checks/
│   │   ├── web/
│   │   │   ├── PathTraversalCheck.java
│   │   │   ├── XxeCheck.java
│   │   │   ├── SqlInjectionCheck.java
│   │   │   ├── CommandInjectionCheck.java
│   │   │   ├── XssCheck.java
│   │   │   ├── JwtKidInjectionCheck.java
│   │   │   ├── SsrfCheck.java
│   │   │   └── HttpMethodFuzzingCheck.java
│   │   ├── infra/
│   │   │   ├── PortScannerCheck.java
│   │   │   ├── SubdomainEnumerationCheck.java
│   │   │   └── FingerprintCheck.java
│   │   └── files/
│   │       ├── SensitiveFileBruteforceCheck.java
│   │       └── CredentialStuffingCheck.java
│   │
│   ├── discovery/
│   │   ├── ParameterDiscovery.java
│   │   ├── impl/
│   │   │   ├── HtmlParameterExtractor.java
│   │   │   ├── JsonParameterExtractor.java
│   │   │   └── LoginFormDetector.java
│   │   └── dictionary/
│   │
│   ├── core/
│   │   ├── HttpClient.java
│   │   ├── HttpRequestBuilder.java
│   │   ├── TcpClient.java
│   │   ├── DnsResolver.java
│   │   └── FileDownloader.java
│   │
│   ├── payload/
│   │   ├── PayloadLoader.java
│   │   └── impl/
│   │       └── JsonPayloadLoader.java
│   │
│   ├── analyzer/
│   │   ├── ResponseAnalyzer.java
│   │   ├── SignatureAnalyzer.java
│   │   ├── TimeBasedAnalyzer.java
│   │   ├── BannerAnalyzer.java
│   │   └── FingerprintAnalyzer.java
│   │
│   ├── chain/
│   │   ├── ChainExecutor.java
│   │   ├── ChainStep.java
│   │   ├── impl/
│   │   │   ├── FindBackupStep.java
│   │   │   ├── ExtractCredentialsStep.java
│   │   │   ├── LoginStep.java
│   │   │   └── SessionGrabStep.java
│   │   └── result/
│   │       └── ChainResult.java
│   │
│   └── report/
│       ├── ReportGenerator.java
│       ├── ConsoleReporter.java
│       └── JsonReporter.java
│
├── src/main/resources/
│   ├── config/
│   │   ├── targets.json
│   │   └── scanner-config.json
│   ├── payloads/
│   │   ├── traversal.json
│   │   ├── xxe.json
│   │   ├── sqli.json
│   │   ├── command.json
│   │   ├── xss.json
│   │   ├── jwt-kid-injection.json
│   │   └── ssrf.json
│   └── dict/
│       ├── common_paths.txt
│       ├── sensitive_files.txt
│       ├── subdomains.txt
│       ├── http_methods.txt
│       └── common_ports.txt
│
├── build.gradle.kts
└── README.md

```

---

### Конфигурация

**`scanner-config.json`**
```json
{
  "web": {
    "pathTraversal": true,
    "xxe": true,
    "sqlInjection": true,
    "commandInjection": false,
    "xss": false,
    "jwtKidInjection": true,
    "ssrf": true,
    "httpMethodFuzzing": true
  },
  "infra": {
    "portScanning": true,
    "subdomainEnumeration": true,
    "fingerprinting": true
  },
  "files": {
    "sensitiveFileBruteforce": true,
    "credentialStuffing": false
  },
  "discovery": {
    "enable": true,
    "maxForms": 20,
    "maxLinks": 100,
    "parseJson": true
  },
  "chain": {
    "enable": false,
    "maxDepth": 5
  },
  "timeout": 10000,
  "threads": 5,
  "timeBasedThreshold": 5000,
  "portScanTimeout": 1000,
  "dnsTimeout": 3000
}
```

targets.json

```json
[
  {
    "url": "https://vuln-app.com/download",
    "parameter": "file",
    "method": "GET",
    "authToken": "",
    "headers": {
      "User-Agent": "VulnScanner/1.0"
    }
  }
]
```
