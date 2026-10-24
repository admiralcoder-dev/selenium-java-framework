# AdmiralCoder — Selenium Java Automation Framework

[AdmiralCoder](https://admiralcoder.com) · [GitHub Organization](https://github.com/admiralcoder-dev)

A professional Selenium WebDriver foundation using **Java 21, JUnit 5, Maven, Page Object Model, explicit waits, and GitHub Actions**.

> **v0.1 scope:** Runs functional UI tests against an isolated inventory demo served locally by an embedded Java HTTP server. These tests do not validate a production application.

## Quick start

Prerequisites: Java 21, Maven 3.9+, Chrome (or Firefox).

```bash
mvn -B verify
mvn -B test -Dbrowser=firefox
mvn -B test -Dheadless=false
```

Selenium Manager resolves compatible browser drivers. CI uses headless Chrome.

## Test coverage

- Landing page and seeded inventory
- Search filtering and empty states
- Add and delete item workflows
- Required fields and negative quantities
- Persistence across page reload
- HTML screenshots on test failure and Surefire XML reports

## Architecture

```text
src/test/java/com/admiralcoder/core/DriverFactory.java
src/test/java/com/admiralcoder/core/DemoServer.java
src/test/java/com/admiralcoder/core/BaseUiTest.java
src/test/java/com/admiralcoder/core/ScreenshotOnFailure.java
src/test/java/com/admiralcoder/pages/InventoryPage.java
src/test/java/com/admiralcoder/tests/InventorySmokeTest.java
src/test/java/com/admiralcoder/tests/InventoryWorkflowTest.java
src/test/resources/inventory.html
.github/workflows/selenium.yml
docs/TEST_STRATEGY.md
```

## Engineering principles

- Page Objects own locators and interactions, not business assertions.
- Explicit waits replace hard-coded sleeps.
- Every test starts in a fresh browser session.
- Screenshots and Maven Surefire reports provide CI evidence.
- Test data is synthetic; no credentials or personal data are required.

**Limitations:** Chrome is the initial CI browser; Firefox is configurable but not yet validated in CI. There is no production integration, cloud grid, or visual regression testing in v0.1.

Licensed under MIT; see [LICENSE](LICENSE).
