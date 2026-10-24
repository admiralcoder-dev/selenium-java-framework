# Selenium Java framework — test strategy

## Objective

Demonstrate an independent Java + Selenium + JUnit test architecture with maintainable Page Objects, explicit waits, failure screenshots and CI evidence.

## Application under test

An isolated browser inventory demo, served by an embedded Java HTTP server on a random localhost port. Synthetic data only; no external credentials or APIs.

## Coverage

- Smoke: page title and initial items
- UI functional: add, delete, search, empty state
- Negative: invalid quantity, missing name
- Persistence: added item remains after reload

## Isolation

Each test creates a fresh browser session. Inventory state is stored in browser localStorage. Parallel test execution is not enabled.

## Exit criteria

Maven `verify` passes on GitHub Actions using Java 21 and headless Chrome; Surefire XML reports are retained.

## Deferred

Cross-browser CI validation, real-world integration, accessibility scans, visual baselines, Selenium Grid, parallel workers and richer reporting.
