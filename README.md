# WordPress UI Test Automation Framework

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.27-43B02A.svg)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10-red.svg)](https://testng.org/)
[![Allure](https://img.shields.io/badge/Allure-2.29-yellow.svg)](https://allurereport.org/)
[![Build](https://img.shields.io/badge/build-Maven-blue.svg)](https://maven.apache.org/)

End-to-end UI test automation for the WordPress admin panel, built with **Selenium WebDriver**, **TestNG**, and the **Page Object Model**. The suite covers authentication, post creation, media upload, quick drafts, and logout, with **Allure** reporting, **SLF4J + Logback** logging, and a **Docker**-based headless run for CI.

> The tests run against the public practice instance
> [`wordpress-test-app-for-selenium.azurewebsites.net`](https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin).

---

## Features

- **Page Object Model** – every WordPress screen is a dedicated page class, keeping locators and actions out of the tests.
- **Singleton WebDriver** – a single browser instance per test is managed in [`Browser`](src/test/java/org/example/webDriver/Browser.java).
- **Browser Factory + Enum** – the configured `browser` value drives [`BrowserFactory`](src/test/java/org/example/webDriver/BrowserFactory.java) to start Chrome, Firefox, Edge, or a mobile-emulated Chrome, no code changes required.
- **Automatic driver management** – [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) downloads the correct driver at runtime; no binaries committed to the repo.
- **Externalized configuration** – browser, base URL, and credentials live in a git-ignored `project.properties` (see `project.properties.example`) and are read via [`PropertyReader`](src/test/java/org/example/utils/PropertyReader.java).
- **Allure reporting** – rich reports with severities, descriptions, links, issues, TMS links, and flaky markers.
- **Structured logging** – debug/info/warn/error logging through SLF4J + Logback (`logback.xml`).
- **Test data generation** – random data helpers in [`TestDataGenerator`](src/test/java/org/example/utils/TestDataGenerator.java).
- **Dockerized run** – a `Dockerfile` provisions Chrome + ChromeDriver + JDK and runs the suite headlessly with `Xvfb`, ready for CI/Jenkins.

---

## Tech Stack

| Area              | Tool / Library                          |
|-------------------|-----------------------------------------|
| Language          | Java 11                                 |
| UI automation     | Selenium WebDriver 4.27                 |
| Test runner       | TestNG 7.10                             |
| Reporting         | Allure 2.29                             |
| Driver management | WebDriverManager 5.9                    |
| Logging           | SLF4J + Logback                         |
| Build             | Maven                                   |
| Containerization  | Docker (Ubuntu + Chrome + Xvfb)         |

---

## Project Structure

```
src/
├── smoke.xml                         # TestNG suite definition
└── test/
    ├── java/org/example/
    │   ├── pages/                    # Page Objects
    │   │   ├── BasePage.java
    │   │   ├── LoginPage.java
    │   │   ├── MainPage.java
    │   │   ├── MainMenuPage.java
    │   │   ├── PostsPage.java
    │   │   ├── PagesPage.java
    │   │   ├── MediaPage.java
    │   │   └── CommentsPage.java
    │   ├── tests/                    # Test classes
    │   │   ├── BaseTest.java         # setup / teardown
    │   │   ├── LoginTest.java
    │   │   ├── PostTest.java
    │   │   ├── MediaTest.java
    │   │   ├── QuickDraftTest.java
    │   │   └── LogOutTest.java
    │   ├── utils/
    │   │   ├── PropertyReader.java
    │   │   └── TestDataGenerator.java
    │   └── webDriver/
    │       ├── Browser.java          # singleton driver
    │       ├── BrowserFactory.java   # driver factory
    │       └── BrowserTypeEnum.java
    └── resources/
        ├── allure.properties
        ├── logback.xml
        └── image.png                 # fixture for the media-upload test
Dockerfile
pom.xml
project.properties.example            # template for the git-ignored project.properties
```

---

## Test Coverage

| Suite          | Scenarios |
|----------------|-----------|
| **LoginTest**  | Valid login; empty password; empty username; empty credentials; invalid credentials |
| **PostTest**   | Create a new post and verify it appears in the list |
| **MediaTest**  | Upload a media file and verify it appears in the library |
| **QuickDraftTest** | Create a quick draft from the dashboard |
| **LogOutTest** | Log out and verify the session ends |

---

## Getting Started

### Prerequisites

- **JDK 11+**
- **Maven 3.8+**
- **Google Chrome** installed (the default browser; the driver is fetched automatically)

### Configuration

Runtime settings live in `project.properties`, which is **git-ignored** so credentials never reach the repo. Create it from the provided template:

```bash
cp project.properties.example project.properties
```

Then fill in the values:

```properties
browser=CHROME
ADMIN_USERNAME=your_admin_username
ADMIN_PASSWORD=your_admin_password
PROD_URL=https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin
```

Supported `browser` values: `CHROME`, `FIREFOX`, `EDGE`, `MOBILE`.

### Run the tests

```bash
mvn clean test
```

The suite executed is defined in [`src/smoke.xml`](src/smoke.xml).

---

## Allure Report

Allure results are written to `target/allure-results` during the test run.

```bash
# Generate and open the report locally
mvn allure:serve

# Or generate a static report into target/site/allure-maven-plugin
mvn allure:report
```

---

## Running in Docker

The provided [`Dockerfile`](Dockerfile) builds a self-contained image with Chrome, ChromeDriver, JDK, and Maven, then runs the suite headlessly using `Xvfb` — ideal for CI pipelines (e.g. Jenkins).

```bash
docker build -t wordpress-ui-tests .
docker run --rm wordpress-ui-tests
```

---

## Design Notes

- **Why a singleton driver?** It guarantees one browser per test method and centralizes lifecycle (`setUp` / `tearDown`) so individual tests stay focused on behavior.
- **Why WebDriverManager?** Driver binaries are version-specific and large. Resolving them at runtime keeps the repository clean and the suite portable across machines and CI agents.
- **Why externalized properties?** The same suite can target different environments and credentials without code changes.

---

## License

This project was built for educational and portfolio purposes.
