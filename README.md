# selenium-java-ci

## 🥇 Introduction 🥇
This is a simple test framework I developed to demonstrate my experience in UI automated tests development. 

It's being developed using **Selenium**, one of the most common UI test libraries currently, written in **Java**.

**TestNG** is a test framework being used to organize and execute the tests (using .xml file combined with annotations)

**Maven** is being used to manage dependencies, build and execute the tests using CLI

## 🧮 Components 🧮
- commons
- pageObjects
- pageUI
  
- tests
- resources

## ⚛️ OOP application ⚛️
- _Inheritance_: (extends)
  - All the page objects extend the `BasePage.java` which contains all common selenium built-in methods (that are optimized to reduce flakiness). These pages objects can re-use all the methods in the `BasePage.java` class (except the private ones)
  - All the test classes extends the `BaseTests.java` which contains the methods to initialize and setup webdriver and quit the webdriver
  - The homepage contains the common elements of other pages such as top menu, footer, etc. Therefore, other page objects extend the HomePageObject to re-use the methods of this page (e.g. click top menu link)
- _Abstraction_: (implements interfaces, extends abstract classes)
  
- _Encapsulation_: (access modifier, getters, setters)
  - All the actions/methods of a page are defined in a page object class. To use the methods in a test, the page object needs to be instantiated and methods should be called through that object
  - In test classes, properties of an account need to be retrived via `Accounts.java` class. Account contains the properties of an account and getter and setter to access properties of this object.
- _Polymorphism_: (overriding - run time, overloading - compile)
  - Overloading: In the `BasePage.java` class, there are more than 1 methods to click an element. Each method has same number of parameters with different data types, or different number of parameters
  - Overriding: Since the page objects extend the `BasePage.java` class, the page object can have a method with same name and parameters as parent class but with different way of implementation

## ▶️ **design patterns** ◀️
- Page Object Pattern
- Factory Pattern
- Singleton Pattern

## 🔄 CI using Github Actions 🔄
A workflow has been set up on this repository using **Github Actions** to trigger the tests run whenever:
- There is a commit to the 'main' branch
- A pull request is created against the 'main' branch
  
The job with the following steps will be executed on a clean instance (latest ubuntu):
- Checkout the repository
- Setup Java 11
- Install latest stable chrome
- Execute the tests

## 🏃 How to run the tests? 🏃
- Set up
  - Install Java 11 (or later)
  - Clone the github repo to your local
  - Make sure your local machine has the latest stable chrome version
- Run test:
  - Right click on the file _src/test/resources/testcases.xml_ and select **_Run As > TestNG Suite_**
  - Or open terminal and cd to project folder. Run test using Maven command `mvn clean test`
  - Or pushing a change to the 'main' branch in a forked repo to trigger workflow

_NOTE_: Because the https://demo.nopcommerce.com/ is a website just for practice purposes, the registered account would be expired after 20 minutes. Therefore, in order to run the successful login test, you need to replace the USER_ACCOUNT in Accounts.java class by a newly registerred account before running the test.

## ⬆️ Potential Improvements ⬆️
- Log
- Extent Report
- Selenium Grid
- Selenium Grid with Docker
- Implement exception class for better debuggability
