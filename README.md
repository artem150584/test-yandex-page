### Installation
Clone. Build using Maven.

`mvn test` for default profile (with Chrome Driver)

`mvn test -P chrome` for Chrome profile (Chrome Driver)

`mvn test -P firefox` for Firefox profile (Firefox Driver)

Note: the browser must have the focus for the tests with @robot tag