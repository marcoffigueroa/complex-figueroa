# Complex-Task

## EdgeDriver Portability
To ensure EdgeDriver portability, the file `msedgedriver.exe` has been added at the following path:  
`test/resources/drivers/msedgedriver.exe`

---

## Overview
This project implements a test automation framework for the web application [SauceDemo](https://www.saucedemo.com/), using Selenium WebDriver, Cucumber (BDD), JUnit, Hamcrest for assertions, and Log4j for logging.  
The framework design applies several design patterns to achieve flexibility, maintainability, and scalability.

---

## Test Tasks

The automated tests validate the login functionality of the SauceDemo application. The main tasks include:

- Navigate to the login page.
- Enter credentials (username and password).
- Clear username and/or password fields.
- Click the login button.
- Validate error messages for invalid credentials or empty fields.
- Validate the page title after a successful login.

**File references:**
- Scenario definitions: `src/test/resources/LoginSteps.feature`
- Step implementations: `src/test/java/com/epam/complex/tests/LoginSteps.java`
- Page Object: `src/test/java/com/epam/complex/models/LoginPage.java`
- Setup/teardown hooks: `src/test/java/com/epam/complex/tests/Hooks.java`

---

## Execution Flow

1. **Start execution**: The runner `LoginRunner.java` is executed, which triggers the scenarios defined in the feature file.
2. **Browser setup**: The `@Before` hook in `Hooks.java` calls `DriverSingleton.getDriver()`, which initializes the WebDriver based on the configured browser.
3. **Driver decoration and adaptation**: The real driver is wrapped by decorators (logging and screenshots) and adapters (for Edge and Chrome), centralizing configuration and extending functionality.
4. **Step execution**: The Cucumber steps (`LoginSteps.java`) interact with the Page Object (`LoginPage.java`), which uses the decorated and adapted driver to interact with the web.
5. **Assertions and validations**: Hamcrest assertions validate titles and error messages.
6. **Teardown**: The `@After` hook closes the browser and releases resources.

---

## Applied Design Patterns

### 1. Singleton
- **File:** `src/test/java/com/epam/complex/drivers/DriverSingleton.java`
- **Purpose:** Ensures only one WebDriver instance exists per execution thread, centralizing its access and management.
- **How it's applied:** The `DriverSingleton` class provides a static method to get the driver, ensuring a single instance per thread. All test code retrieves the driver through this singleton.

### 2. Abstract Factory
- **Files:**
    - Interface: `src/test/java/com/epam/complex/drivers/WebDriverFactory.java`
    - Implementations: `EdgeDriverFactory.java`, `ChromeDriverFactory.java`
- **Purpose:** Allows the creation of WebDriver instances for different browsers in a decoupled and extensible way.
- **How it's applied:** Each factory implements the `WebDriverFactory` interface and returns a browser-specific driver adapter. The singleton uses the factory based on the browser property.

### 3. Decorator
- **Directory:** `src/test/java/com/epam/complex/drivers/decorator/`
    - `LoggingWebDriverDecorator.java`
    - `ScreenshotWebDriverDecorator.java`
- **Purpose:** Adds cross-cutting functionalities (action logging and automatic screenshots) to WebDriver without modifying its original code.
- **How it's applied:** In `DriverSingleton`, after the driver is created by the factory, it is wrapped with the logging and screenshot decorators. All browser actions are thus logged and screenshots are taken automatically.

### 4. Adapter
- **Directory:** `src/test/java/com/epam/complex/drivers/adapters/`
    - `EdgeDriverAdapter.java`
    - `ChromeDriverAdapter.java`
- **Purpose:** Adapts specific drivers (Edge, Chrome) to the standard `WebDriver` interface, centralizing configuration and facilitating implementation changes.
- **How it's applied:** Each adapter implements `WebDriver` and wraps the real driver, handling its configuration. The factories return these adapters, so the rest of the code always interacts with the standard interface.

---

## BDD Approach with Cucumber
- **Feature file:** `src/test/resources/LoginSteps.feature`
- **Step definitions:** `src/test/java/com/epam/complex/tests/LoginSteps.java`
- **Runner:** `src/test/java/com/epam/complex/runners/LoginRunner.java`

The project follows the BDD (Behavior Driven Development) approach using Cucumber, where business scenarios are described in natural language (Gherkin) and linked to Java code through step definitions.

---

## Assertions with Hamcrest
- **Usage:** In the step definitions (`LoginSteps.java`), Hamcrest matchers validate expected results such as page titles and error messages.
- **Example:**
  ```java
  assertThat("Title does not match", actualTitle, equalTo("Swag Labs"));
  ```

---

## Logging with Log4j
- **Configuration:** Log4j is integrated throughout the framework to log relevant information during test execution.
- **Where used:** Classes such as `DriverSingleton`, `Hooks`, `LoginSteps`, and the decorators use Log4j for logging.
- **Benefit:** Enables tracking of execution flow, debugging, and auditing of test actions.

---

## Relevant Folder Structure
```
src/test/java/com/epam/complex/
  drivers/
    DriverSingleton.java
    WebDriverFactory.java
    EdgeDriverFactory.java
    ChromeDriverFactory.java
    adapters/
      EdgeDriverAdapter.java
      ChromeDriverAdapter.java
    decorator/
      LoggingWebDriverDecorator.java
      ScreenshotWebDriverDecorator.java
  models/
    LoginPage.java
  runners/
    LoginRunner.java
  tests/
    Hooks.java
    LoginSteps.java
src/test/resources/
  LoginSteps.feature
  drivers/
    msedgedriver.exe
```

---

## How to Run the Tests

1. Set the desired browser with the system property `browser` (default: edge):
   - From terminal: `mvn test -Dbrowser=chrome`
2. Run the `LoginRunner.java` from your IDE or with Maven:
   - `mvn test`
3. Check the generated logs and screenshots in the corresponding folder.

---

## Credits
Developed by Marco Figueroa for design patterns and test automation practice in Java.
