# User Service

A Spring boot based service for consuming the JSONPlaceholder endpoint.

## Project Structure

```
user-app    -> Application module where the Spring boot application class resides and the fat jar would be created when we build the project
user-rest   -> Rest module where the controller resides and the RestTemplate has been used to consume JSONPlaceholder endpoint
user-common -> Common module where the models reside
```


## How to run the application

1. Run the command `mvn clean install` within your local repository and create a fat jar - *user-app-1.0.0.jar* .
2. Run the *fat jar*  using the below command.
```
java -jar user-app-1.0.0.jar`
```