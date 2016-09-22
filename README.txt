Address Extract Sample Code Package contains 4 main components:
AddressExtract.xml --> Spring configuration where the data injection, SQL connection, and Spring Batch structure take place
AddressExtractProcessor.java --> Main processing java code
AddressExtractOutput.java --> POJO for our output
Agent.java --> POJO for our input

Functionality/Intended Use:
The program will retrieve all Agents from agent table, and process it accordingly before writing the result to a flat file in
the specified directory ${dir}, which is controlled in a separate properties file (omitted from the sample)

Main blueprint/road map for this Address Extract sample program is located in the AddressExtract.xml. To run this program,
a separate configuration is needed, and this configuration will implement Spring CommandLineJobRunner with the XML file as
the argument. This program will not run because I omitted majority of the critical configuration components

AddressExtract.xml contains 3 major parts in its chunk:
--> Reader: reads the data from our agent table, and using parameterized data access (to heighten security against possible
	injection attacks). Map input from database to Agent POJO
--> Processor: process the data retrieved from the reader and produce output to the writer. In here, processor is specified
	in the AddressExtractProcessor.java
--> Writer: writes output from processor to a flat file called AddressExtract.txt

Note: I demonstrated multi-threading with threads 9; therefore, using synchronized reader and writer to prevent incorrect process
