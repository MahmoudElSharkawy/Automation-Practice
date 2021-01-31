# Automation-Practice
This is where I practice Test Automation!

### The main Frameworks included in the project:
* Selenium Webdriver
* Rest-Assured
* TestNG
* Allure Report
* Apachi POI


# Live Project
Included a live project which I summarize my practice with 

### The live project Design implementation:
* Page Object Model (POM) design pattern
* Data Driven framework
* Fluent design approuch (method chaining)
* Have a supporting Utilities package in *src/main/java* file path, named ***"Utils"*** that includes many wrapper methods in static classes which services as a core engine for the project 
* Implementing the ***Test Automation Pyramid*** by have 2 different test automation levels which are SERVICE and GUI layers
* 16 Test Cases total (8 on SERVICE layer and 8 on GUI layer) on this [demo web app](https://www.phptravels.net/)

### How to run the live project:
* A properties file ***"liveproject.properties"*** can be found it *src/main/resources* file path including all the configurations needed in the execution
* Can run the live project Test Suit ***"liveproject.xml"*** in *src/test/resources/TestSuits* file path that includes all the test classes of the live project
* After executing, you can generate the ***Allure Report*** by opening a commandline terminal on the project root path and type `mvn allure:serve`


###### Finally, you can also find a [playlist on Youtube](https://youtube.com/playlist?list=PLmayvCz0Xqr6TT-XJHlPtjDSdJ8WArBHi) (Arabic content) that summarizes and executing the project
