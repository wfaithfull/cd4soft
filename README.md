# Change Detection for Software Engineers
This repo accumulates the code from my blog posts on change detection.

We have the following components:

| Class | Description |
|---|---|
| `Application` | Entry point |
| `DataController` | Exposes HTTP routes |
| `DataProvider` | Provides data for `DataController`, and artificially slows requests to a configurable amount |
| `ChangeDetectionService` | Grabs request times and feeds them into a configured, injected `ChangeDetector<Double>` |

To run the test for CUSUM on the request times for the server, run

`mvn surefire:test -Dtest=DataControllerTest`
