# Change Log

## 2.0.4
> 2024 Apr
* Allow sign-in using admin-portal (for enterprises)

## 2.0.3
> 2024 Mar
* Added back `classpathPrefix` setting for run-mode `standalone`

## 2.0.2
> 2024 Mar
* Ctrl-Click on log `file://` hyperlink now works correctly
* Log colors were missing for run-mode `custom`
* Improved instructions for run-mode
* Improve logging to `Karate Log` output
* For run-mode `standalone` default to JRE bundled with `redhat.java` extension

## 2.0.1
> 2024 Feb
* Updated extension manifest to point to [home page](https://github.com/karatelabs/vscode-extension)

## 2.0.0
> 2024 Feb
* Stopped bundling platform-specific JRE and Karate JAR
* Small footprint, cross-platform extension
* Teams can install [vscode-java-debug](https://github.com/microsoft/vscode-java-debug) for best experience
* Simpler license activation flow
* Offline license activation option
* Removed Postman & OpenAPI import ([contact-us](https://karatelabs.io/contact-us) if you need an alternative)
* Removed K-Flow
* Removed Karate activity-bar and run mode / options menu, launch configurations are sufficient
* Configuration keys changed to start with `karatelabs.karate` (instead of `karatelabs-karate`)

## 1.4.1
> 2023 Dec
* Pro: Improve Debug Server license check
* Update license / links to EULA

## 1.4.0
> 2023 Nov
* Introduced PLUS subscription level in addition to PRO
* To run tests you require at least a PLUS subscription

## 1.2.0
> 2023 Sep
* Pro: fix auth issue for multiple Stripe subscriptions with the same product-code
* Pro: fix Postman import for very large collections
* Pro: support Karate Kafka

## 1.0.0
> 2023 Jul
* Pro: support VS Code launch-configurations
* Pro: improved Postman import - support for GraphQL
* K-Flow: added decision table view

## 0.8.0
> 2023 Apr
* Code Formatting implemented
* Hover to run a single row within a `Scenario Outline`
* Changing run-mode takes effect immediately without re-starting
* Added support for linux-arm64
* Postman import improved, will not stop on un-supported JS, more stable

## 0.6.0
> 2023 Jan
* Improved Postman import (pre-request scripts)
* Inline Summary, Feature and HTTP Report
* [K-Flow](https://www.karatelabs.io/k-flow) - for API Documentation and Test Modeling

## 0.4.0
> 2022 Dec
* API Data import from Postman, OpenAPI etc.

## 0.2.0
> 2022 Sep
* First Release
