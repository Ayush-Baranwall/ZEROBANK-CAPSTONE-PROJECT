# Zero Bank Capstone Project

This repository contains an end-to-end test automation suite for the **Zero Bank** web application. The project demonstrates how to use **Selenium**, **Cucumber (BDD)**, and **TestNG** together to validate critical banking workflows such as:

- **User Authentication**  
  (Login, invalid credentials, etc.)

- **Account Overview**  
  (Verifying account balances and details)

- **Bill Payment**  
  (Paying bills, adding new payees, handling missing data)

- **Fund Transfers**  
  (Transferring funds between accounts, handling edge cases)

- **PDF Statement Downloads**  
  (Verifying file downloads and PDF file integrity)

- **Logout Functionality**  
  (Ensuring secure session termination)

---

## Key Technologies & Frameworks

- **Java 8+**  
- **Maven**: Dependency management and build tool  
- **Selenium WebDriver**: Browser automation  
- **Cucumber**: Behavior-Driven Development (BDD) for writing human-readable feature files  
- **TestNG**: Test runner integration  
- **Extent Reports / Allure** (optional): For generating rich HTML and dashboard-style reports

---

## Configuration

- **`config.properties`**  
  Located in `src/test/resources/config/`. It holds environment-specific data such as browser type, base URL, and credentials.

- **`DriverBase`** and **`ConfigReader`**  
  Classes that handle reading properties and initializing WebDriver sessions.

---

## Reporting

- **Extent Reports** or **Allure**  
  Configured in the `pom.xml` and the TestNG suite file to generate rich, interactive reports.
  
- **HTML Reports**  
  Generated in the `reports` folder (or your configured output directory) after the tests finish executing.

---

## Project Structure

zero-bank-capstone/
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   └── test
│       ├── java
│       │   └── com
│       │       └── zerobank
│       │           ├── pages       (Page Object classes)
│       │           ├── runners     (Cucumber runners/TestNG suite files)
│       │           ├── steps       (Step definitions)
│       │           └── utils       (Utilities, driver setup, config readers)
│       └── resources
│           ├── config             (Properties/config files)
│           └── features           (Cucumber .feature files)
└── reports                         (Generated HTML reports)
