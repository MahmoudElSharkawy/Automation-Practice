# Automation-Practice
This is where I practice Test Automation!

### The main Frameworks included in the project:
* Selenium Webdriver
* Rest-Assured
* TestNG
* Allure Report
* Extent Reports
* Apachi POI


### Project Design:
* Page Object Model (POM) design pattern
* Data Driven framework
* Fluent design approach (method chaining)
* Have a supporting Utilities package in *src/main/java* file path, named ***"Utils"*** that includes many wrapper methods in static classes which services as a core engine for the project 
* Implementing the ***Test Automation Pyramid*** by have 2 different test automation levels which are SERVICE and GUI layers


### How to check the execution logs and open the latest execution reports from GitHub Actions:
* You need to be logged-in to the GitHub as a prerequisite
* Open the GitHub Actions tab
* Open the latest workflow run from the list
* To check the execution logs, click on "Test on Ubuntu" job and open the "Run Tests - Chrome Headless" step and then you can see and check the execution logs
* To open the Allure report, in the *Artifacts* section, Click on the "Allure Report" and then unzip the archive file and then open the "index.html" file (If you are on Windows and the report opened with empty data, you need to open the "allow-file-access_open-report_chrome_windows.bat" file to be able to see the report data)
* To open the Extent report, in the *Artifacts* section, Click on the "Extent Report" and then unzip the archive file and then open the "ExtentReports.html" file


### How to run the project main test cases locally:
* A properties file ***"automationPractice.properties"*** can be found it *src/main/resources* file path including all the configurations needed in the execution
* Can find the test cases in the *src/test/java* folder mainly in the *phptravels.tests* and *restfulbooker.tests* packages
* Can find the test suite for all the main practice test cases in the *src/test/resources/TestSuits* folder in the *automationPractice.xml* file
* To start the execution, please make sure that the "execution.type" property is "Local" if you are running locally then right click on the test suite xml file and click Run As >> TestNG Suit 
* After executing, you can easily generate the ***Allure Report*** by opening a command-line terminal on the project root path and type `mvn allure:serve` (needs to be able to execute mvn commands); Or you can find the Extent Report ***ExtentReports.html*** in the project root path for the latest execution


###### Finally, you can also find a [playlist on Youtube](https://youtube.com/playlist?list=PLmayvCz0Xqr6TT-XJHlPtjDSdJ8WArBHi) (Arabic content) that summarizes and executing the project for some cases
