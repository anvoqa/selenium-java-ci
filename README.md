# selenium-java-ci

## Introduction
This is a simple test framework I developed to demonstrate my experience in UI automated tests development. 
It's being developed using Selenium, one of the most common UI test libraries currently, written in Java.

A workflow has been set up on this repository using Github Actions to trigger the tests run whenever:
- There is a commit to the 'main' branch
- A pull request is created against the 'main' branch

The OOP is applied in the framework as the following:
- Inheritance
- Abstraction
- Encapsulation
- Polymorphism 

Some of the common design patterns are being used as the following:
- Page Object Pattern
- Factory Pattern
- Singleton Pattern

## Components
- commons
- pageObjects
- pageUI
  
- tests
- resources

## How to run the tests?
- Set up
  - Install Java 11 (or later)
  - Clone the github repo to your local
  - Make sure your local machine has the latest stable chrome version
- Run test:
  - Right click on the file _src/test/resources/testcases.xml_ and select **_Run As > TestNG Suite_**
  - Or open terminal and cd to project folder. Run test using Maven command `mvn clean test`


## Potential Improvements
- Log
- Extent Report
- Selenium Grid
- Selenium Grid with Docker
