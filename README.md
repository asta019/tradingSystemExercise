Requirement:
Build a simple trading system as a pure REST API with the endpoints given below. We want the
users to be able to place orders to buy and sell stocks and track the overall value of their
investments. Stocks will have an id, name, and price.
For this example, there is no need to have the user authenticated (but if you can, that would be
better). You can assume that there is only one account/user calling the API.
● Create an endpoint to let the user place orders. Each order should have the stock,
quantity, and price. Each order the user places should be recorded.
● Create an endpoint to retrieve the total value invested in the user’s portfolio. Plus points
if the user can retrieve data on an individual stock.


There are two ways on how to execute these projects:

A. Using an Editor(Intellij)
1. Download Intellij as suggested IDE for this project.
2. To execute the project, go to trading-system folder, under src folder you will see java folder, open this folder and inside this folder, there is com.exercise.trading_system package where you can
see the TradingSystemApplication.java.
3. Download MYSQL database and create a database name: tradingdb   
    CREATE DATABASE tradingdb;


B. By running on command line.
Required software:
-JDK
-Maven

1. Install JDK on your local machine.
2. Download Maven since we are using maven. Locate the download files to c:\program files\.
3. Search for Environment Variables. Click environment varialbe button. Select Path and click add new button. Type the path 
where Maven folder is located. Find the bin folder and write it on required path box. Example:
C:\Program Files\Maven\apache-maven-3.9.9\bin
4. For windows, run cmd.
Locate the folder where pom.xml is located.
Example : 
C:\Users\FCGES\Desktop\SpringBootExercises\trading-system\trading-system
5. Type : mvn spring-boot:run to run the project in command.


Note: These are the dependencies needed:
In trading-system folder, you will see the pom.xml where all dependecies are placed. 
  -Spring Web (For Restful API)
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
  -Spring Data JPA (Responsible for the interaction to databases.)
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>
  -Lombok (This will allow you to automically used the methods for class like setter,getter, constructor etc...)
     <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
      </dependency>
  -MySQL Driver (This is the driver to connect to MYSQL DB)
     <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
      </dependency>

   
Run the endpoints using an API tools. Here are the list of API to be executed:

Endpoints : 
To create record for stock:
    http://localhost:8080/api/stock/create
Sample data to be submitted:
  {
   "name" : "SM",
   "price" : 1000.00
  }

To create record for order:
    http://localhost:8080/api/order/create
Sample data :
  {
    "stockId":1,
    "quantity":50.00,  
    "price":100
  }

To view all records from order table:
    http://localhost:8080/api/order/viewAll

To get the total price per investment: 
    http://localhost:8080/api/order/totalOrder/1



Running Test cases using command line:
1. Navigate to your project directory: Open your terminal or command prompt and navigate to the root directory of your project where the pom.xml file is located.
            -cd /path/to/your/project
2. Run the JUnit tests using Maven: To run all the tests in your project, use the following command:
            -mvn test
3. Run a specific test class: If you want to run a specific test class, use this command:
            -mvn -Dtest=ClassName test
            Ex: mvn -Dtest=OrderControllerTest test

4. Run a specific test method: To run a specific test method within a class:
           -mvn -Dtest=ClassName#methodName test
           Ex: mvn -Dtest=OrderControllerTest#getTotalInvested test





