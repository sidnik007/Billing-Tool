## Billing Tool Kata
This is a Billing Application which calculates the total price of items in the basket.

### Running the application
Makefile is used for running the application. Give the following command to run the application `make run`

### Specifications
Specifications are converted into executable feature files.

## Assumptions
In order to complete the kata some assumptions have been made
* Product once added to the basket will not change
* Discount is tied to the product
* The Unit for Products is not used in the code but is still present as a requirement
* Stock items do not change
* A product can be added only once per calculation from command line
* When 4 tins of soup are added along with 2 bread loaves, the discount is calculated only on a single loaf

## Reports
The reports are available locally, after running a build using `make`.

* [Checkstyle Report Main](build/reports/checkstyle/checkstyleMain/report.html)
* [Checkstyle Report Test](build/reports/checkstyle/checkstyleTest/report.html)
* [PMD Report Main](build/reports/pmd/pmdMain/report.html)
* [PMD Report Test](build/reports/pmd/pmdTest/report.html)
* [SonarLint Report Main](build/reports/sonarlint/sonarlintMain/report.html)
* [SonarLint Report Test](build/reports/sonarlint/sonarlintTest/report.html)
* [Test Report](build/reports/test/test/html/index.html)
* [Jacoco Test Coverage Report](build/reports/jacoco/test/html/index.html)
