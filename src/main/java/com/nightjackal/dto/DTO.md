Отличный запрос. Делаю полноценный `DTO.md` для GitHub.

---

# 📦 Data Transfer Objects (DTO)

Все DTO проекта `NightJackal`.  
Пакет: `com.nightjackal.dto`

---

## Навигация

1. [ScanConfig](#1-scanconfigjava)
2. [Target](#2-targetjava)
3. [Payload](#3-payloadjava)
4. [ResponseData](#4-responsedatajava)
5. [CheckResult](#5-checkresultjava)
6. [DiscoveredEndpoint](#6-discoveredendpointjava)
7. [DiscoveredParameter](#7-discoveredparameterjava)
8. [FormField](#8-formfieldjava)
9. [OpenPort](#9-openportjava)
10. [Subdomain](#10-subdomainjava)
11. [Fingerprint](#11-fingerprintjava)
12. [ChainStep](#12-chainstepjava)
13. [ChainResult](#13-chainresultjava)

---

## 1. `ScanConfig.java`

**Назначение:** Настройки сканирования — включенные проверки, таймауты, пороги.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `pathTraversal` | `boolean` | Включить Path Traversal |
| `xxe` | `boolean` | Включить XXE |
| `sqlInjection` | `boolean` | Включить SQL Injection |
| `commandInjection` | `boolean` | Включить Command Injection |
| `xss` | `boolean` | Включить XSS |
| `jwtKidInjection` | `boolean` | Включить JWT Kid Injection |
| `ssrf` | `boolean` | Включить SSRF |
| `httpMethodFuzzing` | `boolean` | Включить HTTP Method Fuzzing |
| `portScanning` | `boolean` | Включить Port Scanning |
| `subdomainEnumeration` | `boolean` | Включить Subdomain Enumeration |
| `fingerprinting` | `boolean` | Включить Fingerprinting |
| `sensitiveFileBruteforce` | `boolean` | Включить Sensitive File Bruteforce |
| `credentialStuffing` | `boolean` | Включить Credential Stuffing |
| `discoveryEnabled` | `boolean` | Включить автоопределение параметров |
| `maxForms` | `int` | Максимум форм для парсинга |
| `maxLinks` | `int` | Максимум ссылок для парсинга |
| `parseJson` | `boolean` | Парсить JSON-ключи |
| `parseHeaders` | `boolean` | Парсить заголовки |
| `parseCookies` | `boolean` | Парсить Cookies |
| `chainEnabled` | `boolean` | Включить цепочки атак |
| `chainMaxDepth` | `int` | Максимальная глубина цепочки |
| `timeout` | `int` | Таймаут HTTP-запроса (мс) |
| `threads` | `int` | Количество потоков |
| `timeBasedThreshold` | `int` | Порог времени для time-based атак (мс) |
| `portScanTimeout` | `int` | Таймаут сканирования порта (мс) |
| `dnsTimeout` | `int` | Таймаут DNS-запроса (мс) |
| `connectionTimeout` | `int` | Таймаут соединения (мс) |

---

## 2. `Target.java`

**Назначение:** Цель для сканирования.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `url` | `String` | Базовый URL цели |
| `parameter` | `String` | Основной параметр для инъекции |
| `method` | `String` | HTTP-метод (`GET`, `POST`, `PUT`, `DELETE`) |
| `authToken` | `String` | Bearer-токен (если есть) |
| `headers` | `Map<String, String>` | Дополнительные заголовки |
| `bodyTemplate` | `String` | Шаблон тела запроса ({{payload}} для вставки) |
| `contentType` | `String` | Content-Type (application/json, application/xml и т.д.) |

---

## 3. `Payload.java`

**Назначение:** Пейлоад для инъекции.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `id` | `String` | Уникальный идентификатор |
| `name` | `String` | Название пейлоада |
| `value` | `String` | Строка пейлоада |
| `type` | `String` | Тип: `traversal`, `xxe`, `sqli`, `command`, `xss`, `jwt`, `ssrf` |
| `description` | `String` | Описание |
| `encoding` | `String` | Кодировка: `url`, `base64`, `none` |
| `tags` | `List<String>` | Теги для фильтрации (`time_based`, `error_based`, `login_bypass`) |

---

## 4. `ResponseData.java`

**Назначение:** Ответ HTTP-сервера.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `statusCode` | `int` | HTTP-статус |
| `contentLength` | `long` | Длина тела ответа |
| `responseTime` | `double` | Время ответа (сек) |
| `body` | `String` | Тело ответа (первые N символов) |
| `headers` | `Map<String, String>` | Заголовки ответа |
| `url` | `String` | Финальный URL (после редиректов) |
| `error` | `String` | Текст ошибки (если есть) |
| `isTimeout` | `boolean` | Флаг таймаута |
| `redirectChain` | `List<String>` | Цепочка редиректов |

---

## 5. `CheckResult.java`

**Назначение:** Результат одной проверки уязвимости.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `checkName` | `String` | Название проверки |
| `targetUrl` | `String` | URL цели |
| `parameter` | `String` | Имя параметра |
| `payload` | `String` | Использованный пейлоад |
| `vulnerable` | `boolean` | Уязвимость найдена |
| `isLoginForm` | `boolean` | Цель — форма логина |
| `bypassType` | `String` | Тип обхода (`sqli`, `nosqli`, `log4j`) |
| `details` | `String` | Детали результата |
| `statusCode` | `int` | HTTP-статус ответа |
| `responseTime` | `double` | Время ответа |
| `evidence` | `String` | Строка из ответа, подтверждающая уязвимость |

---

## 6. `DiscoveredEndpoint.java`

**Назначение:** Найденный эндпоинт.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `path` | `String` | Путь эндпоинта |
| `method` | `String` | HTTP-метод |
| `statusCode` | `int` | Статус ответа |
| `parameters` | `List<String>` | Список параметров |
| `contentType` | `String` | Content-Type |
| `contentLength` | `int` | Длина ответа |
| `headers` | `Map<String, String>` | Заголовки |
| `isLoginEndpoint` | `boolean` | Является ли эндпоинт логином |

---

## 7. `DiscoveredParameter.java`

**Назначение:** Автоопределенный параметр.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `name` | `String` | Имя параметра |
| `source` | `String` | Источник: `url`, `form`, `json`, `header`, `cookie` |
| `httpMethod` | `String` | HTTP-метод |
| `exampleValue` | `String` | Пример значения |
| `isSensitive` | `boolean` | Чувствительный параметр (`password`, `csrf_token`) |
| `isLoginField` | `boolean` | Это поле логина/пароля |
| `formAction` | `String` | Action формы (если из формы) |
| `inputType` | `String` | Тип поля (`text`, `password`, `hidden`, `email`, `file`) |
| `isRequired` | `boolean` | Обязательное поле |

---

## 8. `FormField.java`

**Назначение:** Поле HTML-формы.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `name` | `String` | Имя поля |
| `type` | `String` | Тип: `text`, `password`, `hidden`, `email`, `file`, `checkbox`, `radio` |
| `value` | `String` | Значение (если есть) |
| `placeholder` | `String` | Плейсхолдер |
| `isRequired` | `boolean` | Обязательное |
| `isDisabled` | `boolean` | Отключенное |

---

## 9. `OpenPort.java`

**Назначение:** Открытый порт.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `port` | `int` | Номер порта |
| `protocol` | `String` | Протокол: `TCP`, `UDP` |
| `service` | `String` | Сервис: `ssh`, `http`, `mysql`, `redis` |
| `banner` | `String` | Баннер сервиса |
| `version` | `String` | Версия (из баннера) |
| `isOpen` | `boolean` | Открыт ли порт |

---

## 10. `Subdomain.java`

**Назначение:** Найденный поддомен.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `domain` | `String` | Домен (admin.target.com) |
| `fullUrl` | `String` | Полный URL (https://admin.target.com) |
| `statusCode` | `int` | HTTP-статус |
| `ipAddress` | `String` | IP-адрес |
| `isAlive` | `boolean` | Доступен |
| `cname` | `String` | CNAME-запись (если есть) |

---

## 11. `Fingerprint.java`

**Назначение:** Технологический стек.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `server` | `String` | Веб-сервер (nginx/1.18.0) |
| `framework` | `String` | Фреймворк (Spring Boot, Django) |
| `language` | `String` | Язык (Java, PHP, Python) |
| `cms` | `String` | CMS (WordPress, Joomla) |
| `database` | `String` | БД (PostgreSQL, MySQL) |
| `headers` | `Map<String, String>` | Заголовки ответа |
| `cookies` | `List<String>` | Cookies (JSESSIONID, PHPSESSID) |
| `technologies` | `List<String>` | Дополнительные технологии |

---

## 12. `ChainStep.java`

**Назначение:** Шаг цепочки атак.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `name` | `String` | Название шага |
| `description` | `String` | Описание |
| `success` | `boolean` | Успешно выполнен |
| `result` | `Object` | Результат (файл, креды, сессия) |
| `subSteps` | `List<ChainStep>` | Подшаги |
| `error` | `String` | Ошибка (если есть) |
| `executionTime` | `long` | Время выполнения (мс) |

---

## 13. `ChainResult.java`

**Назначение:** Результат цепочки атак.

### Поля

| Поле | Тип | Описание |
|------|-----|----------|
| `targetUrl` | `String` | Целевой URL |
| `isCompromised` | `boolean` | Компрометация достигнута |
| `steps` | `List<ChainStep>` | Все шаги цепочки |
| `finalAccess` | `String` | Финальный доступ (админка, сессия, шелл) |
| `summary` | `String` | Краткое описание |
| `totalTime` | `long` | Общее время выполнения (мс) |

---

## 🔄 Связи между DTO

```
ScanConfig (настройки)
│
▼
Target (цель)
│
├── ParameterDiscovery → DiscoveredParameter → Target (новый)
│
▼
Check (каждая проверка)
│
├── Payload (из PayloadLoader)
│
├── HttpClient → ResponseData
│
▼
CheckResult
│
▼
ChainExecutor (опционально)
│
├── ChainStep
│
▼
ChainResult
│
▼
ReportGenerator → консоль / JSON
```

---

## 📌 Итог

| № | DTO | Назначение |
|---|-----|------------|
| 1 | `ScanConfig` | Настройки сканирования |
| 2 | `Target` | Цель для сканирования |
| 3 | `Payload` | Пейлоад уязвимости |
| 4 | `ResponseData` | HTTP-ответ |
| 5 | `CheckResult` | Результат проверки |
| 6 | `DiscoveredEndpoint` | Найденный эндпоинт |
| 7 | `DiscoveredParameter` | Автоопределенный параметр |
| 8 | `FormField` | Поле HTML-формы |
| 9 | `OpenPort` | Открытый порт |
| 10 | `Subdomain` | Найденный поддомен |
| 11 | `Fingerprint` | Технологический стек |
| 12 | `ChainStep` | Шаг цепочки атак |
| 13 | `ChainResult` | Результат цепочки атак |

