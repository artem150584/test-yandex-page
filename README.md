### Installation

mvn -Ddriver=firefox -Dwebdriver.gecko.driver=<path> test

or

mvn -Ddriver=chrome -Dwebdriver.chrome.driver=<path> test 

<path> - path to webdriver

Note: the browser must have the focus for the tests with @robot tag