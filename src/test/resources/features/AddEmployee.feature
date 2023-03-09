Feature: Add Employee

  Background:
    #Given user is navigated to HRMS application
    When user enters valid username and valid password
    And user clicks on login button
    When user clicks on PIM option
    And user clicks on Add Employee button

  @sprint3 @regression
  Scenario: Adding one employee
    #Given user is navigated to HRMS application

    And user enter firstname and lastname
    And user clicks on save button
    Then employee added successfully

  @test1
  Scenario: Adding one employee using feature file

    And user enter "zalaman" and "aliaa"
    And user clicks on save button
    Then employee added successfully

  @outline
  Scenario Outline: Adding multiple employees using feature file
    And user enter "<firstName>" and "<lastName>" for adding multiple employees
    And user clicks on save button
    Then employee added successfully
    Examples:
      | firstName | lastName |
      |gulnamm     |mazar     |
      |rampalf     |chambel   |
      |azamc       |asel      |

  @datatable
  Scenario: Adding multiple employee using data table
    When user adds multiple employees and verify they are added successfully
    |firstName|middleName|lastName|
    |zara     |MS        |camilullah|
    |birgul   |MS        |ozgin     |
    |alina    |MS        |bob       |

  @exel
  Scenario: Adding multiple employees using exel file
    When user adds multiple employee from exel using "EmployeeData" and verify it

  @db
  Scenario: Adding employee and verify it is stored in database
    And user enter "Mansoor" and "Raufi"
    And user captures employee id
    And user clicks on save button
    Then employee added successfully
    And added employee is displayed in database

    @database
  Scenario: Add employee from frontend and get data from db to verify it
    And user enter "Saza" and "Andres"
    And user captures employee id
    And user clicks on save button
    And added employee is available in my database
