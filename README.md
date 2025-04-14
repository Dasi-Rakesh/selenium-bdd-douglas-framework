# Selenium BDD Framework for Douglas.de

This repository contains a Selenium BDD framework designed for automated testing of the [Douglas.de](https://www.douglas.de) e-commerce platform, with a primary focus on Parfum product filtering functionality.

---

## 📁 Framework Structure

selenium-bdd/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pageObjects/
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── HomePage.java
│   │   │   │   ├── ParfumPage.java
│   │   │   │   ├── SignInPage.java
│   │   │   │   └── SignUpPage.java
│   │   │   ├── utils/
│   │   │   │   ├── AlertHandler.java
│   │   │   │   ├── ConfigReader.java
│   │   │   │   ├── DriverManager.java
│   │   │   │   ├── ExcelDataReader.java
│   │   │   │   ├── PageUtils.java
│   │   │   │   ├── ReportManager.java
│   │   │   │   └── TestHooks.java
│   ├── test/
│   │   ├── java/
│   │   │   ├── runners/
│   │   │   │   └── TestRunner.java
│   │   │   ├── stepDefinitions/
│   │   │   │   ├── ParfumSteps.java
│   │   │   │   ├── SignInStepDefinitions.java
│   │   │   │   └── SignUpStepDefinitions.java
│   │   ├── resources/
│   │   │   ├── features/
│   │   │   │   ├── parfum.feature
│   │   │   │   └── userAccount.feature
│   │   │   ├── testData/
│   │   │   │   ├── Filters.xlsx
│   │   │   │   └── ParfumFilters.xlsx
│   │   │   └── config.properties
├── pom.xml


---

## 🔑 Key Components

### 1. Page Objects

- **BasePage**: Common page methods and smart waits
- **HomePage**: Handles cookie prompts and navigation
- **ParfumPage**: Handles Parfum category and filtering
- **SignInPage / SignUpPage**: User authentication and registration pages

### 2. Utilities

- **DriverManager**: WebDriver lifecycle
- **ExcelDataReader**: External data handling from Excel
- **ConfigReader**: Loads configuration settings
- **ReportManager**: ExtentReports integration
- **AlertHandler**: Browser alert handling
- **TestHooks**: Cucumber hooks and setup

### 3. Test Implementation

- **Feature Files**: Gherkin-based tests
    - `parfum.feature`
    - `userAccount.feature`

- **Step Definitions**: Maps Gherkin steps to Java code
- **Test Runner**: Executes tests using Cucumber with JUnit

---

## ⚙️ Technical Specifications

### ✅ Framework Capabilities

- **Page Object Model**: Clean structure with reusable base classes
- **Reporting**: ExtentReports with screenshots on failure
- **Reusable Utilities**: Shared across test cases
- **External Data**: Excel-based, no hardcoded values
- **Parameterization**: Fully data-driven test support
- **Multi-browser Support**: Managed via WebDriverManager
- **Parallel Execution**: Enabled for faster test cycles
- **CI/CD Ready**: Project structure suited for pipelines

---

## 🔁 Sample Test Flow (Parfum Filtering)

1. Navigate to `https://www.douglas.de`
2. Accept cookies
3. Select **Parfum** category
4. Apply filters (price, brand, product type, etc.)
5. Verify the filtered product list

---

## 📊 Test Data Management

- **File**: `ParfumFilters.xlsx`
- **Columns**:
    - Price
    - Product Type
    - Brand
    - For Whom
    - Special

---

## 🛠️ Configuration

- **File**: `config.properties`
- **Contains**:
    - Base URLs
    - Browser settings
    - Timeout values
    - Path configurations

---

## ✅ Best Practices

- **Smart Waiting**: Explicit and fluent wait strategies
- **Atomic Tests**: One feature per scenario
- **Separation of Concerns**: Business logic separated from test logic
- **Exception Handling**: Robust and informative
- **Visual Evidence**: Screenshots on test failure

---

## 🚀 Execution Instructions

1. Configure settings in `config.properties`
2. Ensure Excel data is in `src/test/resources/testData/`
3. Run tests via:

```bash
mvn clean test
   ```

4. View reports in `target/reports/`