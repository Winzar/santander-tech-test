# Santander Tech Test 

## Description
This is an application that uses Spring Boot and Java to validate currency exchange rates, apply commission, and display the most recent exchange rate.
Spring boot settings

## Spring Boot Settings
| Setting | Selected Option |
| ------ | ------ |
|	Project | Maven Project |
|	Language | Java |
|	Spring Boot | 2.7.0 |
|	Packaging | Jar |
|	Java | 11 |
| Dependencies | Spring Web | 

Setup folder created using https://start.spring.io/


## Relevant Files and Descriptions

Relevant files can be found in src/main/java/com/example/demo/

### CSVReader.java
-	The class was set up as a component to integrate with the Spring application - making the application run the CSVReader class upon start up. 
-	Using the Price object that is defined in Price.java, a hash map was set up to store the instrument name/currency being exchanged alongside its corresponding line.
    - A hash map was chosen over an array list for this test, due to the hash map automatically keeping the latest currency exchange rate and removing the previous currency exchange rate – due to duplicate keys not being allowed.
-	Method - loadData
    -	A scanner was used to read Data.csv, with a catch statement being used in case of there being an incorrect file path.
    - While the scanner iterates through the file it appends the line, if the line is empty or contains an ellipsis (a character that was included in the data) it will skip it. Otherwise, the scanner will append the line and will apply the getPriceFromLine method. The Price Object will then be put into the price has map, along with the corresponding currency exchange rate. 
-	Method – getPriceFromLine 
    - The getPriceFromLine method splits the data into an array and cleans the individual inputs by applying trim, while also applying the bidAskComparison (from the PriceValidation class) and the addCommision method (from the CommisionsCalculator class). 
    - The Price object for the respective line is then formed and stored within the loadData method after the method is applied.
- Method - getPriceByInstrumentName 
    - The method is used to obtain the key pairing within the hash map for a given currency exchange rate, later used in PriceController.java.

### CommissionCalculator.java 
-	The class stores the two methods addCommisionBid and addCommisionAsk, which take a given margin percentage and add (for the asking price) and subtract (for the bidding price)  
-	Constants were used for the commission percentage to ensure that they couldn’t be changed elsewhere.
-	The methods for adding/subtracting the commission was split into two in order to reduce error and improve code readability.

### Data.csv
-	The file that contains the currency exchange rate data used in CSVReader.java.
-	Could have been manually stored in an object – but for the intention of improving the application’s functionality, it was kept in a CSV so that a scanner could be used. 

### Main.java
-	Was used to test locally and kept for the purpose of further testing within each class. 

### MarketFeed.java
-	Dummy interface to mimic an interface being called, rather than being read from a CSV – for the sake of functionality dummy interface was not connected and was replaced by CSV scanner. 

### Price.java
-	Price object for storing the values of id, instrumentName, bid, ask and timestamp.
-	Given its own separate class for improved code readability. 
-	Override used at the bottom to allow for the object to be printed as a string – allowing for more effective error checking. 
-	An object was chosen for storing data due to its ability to store multiple types of data.

### PriceController.java
-	Using Spring Boot a server was locally hosted. Postman was used to test functionality and ensure the http requests were being fulfilled/functional. 
-	Autowired was used to integrate CSVReader into the class. in order to use the getPriceByInstrumentName method.
-	Within GetMapping, the pathway was set to accept two individual currencies inputs with the strings fromExchangeCurrency and toExchangeCurrency storing those inputs.
-	Using the getPriceByInstrumentName method (from CSVReader.java), the exchange currency strings are used to search for their respective currencies within the Price hash map - calling the respective Price object for the exchange rate.
-	toUpperCase was used due to the CSV files having the currency exchange rate capitalised – preventing issues with case sensitivity.

### PriceValidation.java
-	Using the bidAskComparison method, the class was used to check the data to see if the bid was less than the ask. If the condition is not met, a run-time exception is thrown.

### SantanderJavaChallengeApplication.java
-	Used by Spring Boot to run the application.


