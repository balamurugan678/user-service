# User Service

A Spring boot based service for consuming the JSONPlaceholder endpoint.

## Project Structure

```
user-app    -> Application module where the Spring boot application class resides and the fat jar would be created when we build the project
user-rest   -> Rest module where the controller resides and the RestTemplate has been used to consume JSONPlaceholder endpoint
user-common -> Common module where the models reside
```

## API Endpoints

```
GET /users              -> List all Users
GET /user/{userId}      -> Get an individual User
POST /user              -> Create an User
DELETE /user/{userId}   -> Delete an User
```

## Project Integration Test Framework

1. As the application has been written in Spring Boot framework, Spring boot's out-of-the-box test kit has been used here.
2. The Integration test class is placed under the module - *user-app* and the test class is *UserIntegrationTest*
3. The integration test class has been run by *SpringRunner*
4. The Spring boot test environment has been declared and a random port has been declared.
5. The MockMvc has been used to send the API calls to the */users* endpoint.
6. The assertions have been placed using *andExpect* method call and using JSONPath the Response body has been inspected.
7. The following are the tests in the project.
```
getUsersTest()
getUsersHeadersTest()
getIndividualUserTest()
postUserTest()
getInvalidUserTest()
deleteIndividualUserTest()
```


## How to run the application

1. Run the command `mvn clean install` within your local repository and create a fat jar - *user-app-1.0.0.jar* .
2. Run the *fat jar*  using the below command.
```
java -jar user-app-1.0.0.jar
```