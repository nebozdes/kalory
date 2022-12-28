# Kalory - exhibition project 

### Overview

Kalory is a light-weight calories counter with possibility to track person's weight and set up 
and reach up some weight target.

### Tech stack & architecture

* Spring (version 2.7.7, includes Data JPA, Web, Security, Validation)
* Postgres, Flyway
* TestContainers, RestAssured, Hamcrest, JUnit5, Mockito, AssertJ
* Lombok, common-langs3, org.json, some additional Jackson plugins
* CQRS approach with commands and queries objects segregated

### Models
* User - simply the user of the system
* Product - any product which have some base nutrition characteristics calculated
(calories, carbs, proteins, lipids etc).
* ConsumedProduct - tracked consumption of some Product by some User
* WeightCheck - tracked measurement of person's weight at some moment of time
* WeightTarget - some result (in terms of weight gain/loss) set by person with possible deadline (optional)

### Testing approach & coverage

As a testing approach following combination is chosen:
* Unit-tests powered by JUnit5 and Mockito - these tests cover mission-critical 
business logic like Commands and Queries, and also covers mappers.
It also allows to keep unit-tests as fast as possible without setting the spring context up
* Integration-tests (or maybe end-2-end API tests) powered by RestAssured, Hamcrest
and TestContainers - these tests allow to check all the features of the app including
  (de)serialization, authorization, authentication, integration with DB and so on. 
Here only one spring context is set and all the tests are designed to be independent

### Nice to have

* Extend the amount of properties for a product with some micro-elements and additional factors
* Add social feature - weight feed, discussions