# Run Maven for Different Phases
OnlineMarket is a Java-based application that simulates an online marketplace. It allows for managing products, employees, and related information. The project is organized into a clear package structure to ensure readability and maintainability.

## Validate
Description: Ensures that the project is correctly configured.
Result: Maven checks if all required configurations are set up in the pom.xml file

In my case -> BUILD SUCCESS

## Compile
Description: Compiles the Java source code in the src/main/java directory into .class files in the target/classes directory.
Result: The project is successfully compiled, and the compiled .class files are stored in target/classes.

In my case -> BUILD SUCCESS

## Test
Description: Executes unit tests located in the src/test/java directory
Result: Maven runs all tests and generates a report. If a test fails, the build stops with an error.|

In my case -> BUILD SUCCESS

## Install
Description: Installs the packaged .jar file into the local Maven repository (~/.m2/repository). This allows other Maven projects to use this project as a dependency.
Result: The .jar file is stored in the Maven local repository.

In my case -> BUILD SUCCESS

## Deploy
Description: Deploys the .jar file to a remote Maven repository.
Result: If a remote repository is configured, the .jar file will be uploaded.

In my case -> Deployment failed: repository element was not specified in the POM inside distributionManagement element or in -DaltDeploymentRepository=id::layout::url parameter ->

# Run Project with Maven
To run the project and set the log level dynamically, follow these steps:

## Open the project directory in the terminal
Navigate to the root directory of the project where the pom.xml file is located.

## Set the log level using an environment variable

#### For Windows (CMD):
```cmd
set LOG_LEVEL=warn
mvn compile
mvn exec:java -Dexec.mainClass="com.solvd.onlinemarket.Main"


