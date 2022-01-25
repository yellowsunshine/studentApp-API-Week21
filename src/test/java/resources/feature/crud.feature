Feature: Testing different request on the student application

  @SMOKE @REGRESSION
  Scenario: Check if the user can get all student details
    When User sends a GET request to get all the students details
    Then User gets a valid response with status code 200

  @SANITY @REGRESSION
  Scenario Outline: Check if user can create, read, update and delete a record
    When User creates a new student providing "<firstName>" "<lastName>" "<email>" "<programme>" "<course1>" "<course2>"
    Then verifies that the student with email"<email>" is created
    And updates the created record with new email using PUT request and details "<firstName>" "<lastName>" "<programme>" "<course1>" "<course2>"
    And deletes the updated record using id
    And verifies that the record is deleted

    Examples:
      | firstName | lastName | email              | programme        | course1 | course2 |
      | Prime     | prime1   | prime1@gmail.com   | Computer Science | JAVA    | Ruby    |
      | Sam       | Mandes   | prime12@gmail.com  | Computer Science | JAVA    | Ruby    |
      | Sam       | Mandes   | prime123@gmail.com | Computer Engg    | php     | C#      |



