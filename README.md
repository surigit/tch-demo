# tch-demo
The project is to a demo a use case on csv data file load and search. The csv file is under `src/main/resources`
## Prerequisites
- Maven
- JDK8 
## Build with Test execution
`mvn clean install`
## Execution with search criteria
### Identify Usage
`java -jar target/tch-demo-1.0.0.jar`
### Search using bank name
`java -jar target/tch-demo-1.0.0.jar -bank "Friendly Bank"`
### Search using type
`java -jar target/tch-demo-1.0.0.jar -type ATM`
### Search using zipcode
`java -jar target/tch-demo-1.0.0.jar -zipcode 75201`
### Search using city
`java -jar target/tch-demo-1.0.0.jar -city Dallas`
### Search using state
`java -jar target/tch-demo-1.0.0.jar -state TX`
### Search using city & state
`java -jar target/tch-demo-1.0.0.jar -city Dallas -state TX`


