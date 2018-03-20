# Neolynk: Java technical test

This project is the result of the technical test

# Goal
Create a small java project to manage users and their bank accounts. Some rules to help you model:
 - A user can have multiple accounts
 - User must have some personal information like first and last name, age, address, phone
etc.
 - Account must have some attributes as well like date of creation, balance etc.
 
Services/functionalities to be developed:
 - Develop functionalities to add, delete, update and read users.
 - Develop functionalities to add, delete, update and read accounts.
 - Develop functionalities to withdraw and deposit money in bank accounts.
 - Develop a functionality to link accounts to users as well.
 - Develop a functionality to find all the accounts of a given user.
 - Develop a functionality to find the sum of balances of all the accounts of a given user (to
find out the wealth of a user).

Some guidelines:

 - Develop this mini project in TDD (test driver development or test first) manner.
 - Don’t use any database to store users and accounts. You can use a simple java data
structure such as list, map etc. to store your data in memory.
 - No need to use any framework.
 - No need to create any GUI (user interface), just proper unit tests to cover all the cases
will do.

Stack technique to be used:

 - Java8
 - Maven
 - Junit (for TDD)
 - GIT for source control
 - Functional programming of Java8 if possible

##  How to:

Follow this steps

> git clone https://github.com/pi-2r/neolynkTest.git

> cd neolynkTest

> mvn clean install

Result:
![enter image description here](https://image.ibb.co/b5yV2x/neolynk1.png)
## How to run great interface ?

**note:** it's just for fun

run this command in your neolynkTest folder:

 - mvn spring-boot:run
 
 Result:
![enter image description here](https://image.ibb.co/bXmNFH/neolynk2.png)

In your browser, open this page: http://localhost:8080/swagger-ui.html#/

Result:
![Swagger documentation](https://image.ibb.co/j7ZZvH/neolynk3.png) 
