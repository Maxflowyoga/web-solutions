Take-home Coding Assignment for McCarthy, Burgess, and Wolff inc.
-
Run Solution by importing project into Visual Studio and right click, and
right-click project named, MBandWtasks,"Run Debugging Solution" to load SwaggerUI in web browser
for dynamic user fun testing and see natively the JSON outputs as Postman/APIs would see.
-
Sample SwaggerUI outputs on in the 'mbandw_output_docs' folder for view
+folder also contains mock layouts for database table, insert statement, update statement, and stored procedure for upsert logic.
+database is mocked in MySQL until further clarification is achieved on what databases we should be using downstream
to save the data, whether on-premise our cloud environment Database connection strings to create for Dev/QA/Production environments
with the mbandw's Team ecosystem. Thank you.
-


Details:

Testers should have the appropriate IDE installed.   
	•	Visual Studio 2019 or later (Windows), Visual Studio for Mac 2019 or later (MacOS) 

A very simple RESTful application that demonstrates the ability to  
	•	Add a new Task  
	•	Update Tasks that already exist.
	•   Stores data in memory and can implement any database once designated

Overall Requirements
	•	Visual Studio 2019 or Visual Studio 2022 should be used to run the application 
	•	The application is written as an ASP.Net Core Web API application (ASP.Net Core 6/ C#)
		Version: Microsoft.NETCore.App/6.0.33
	•	Unit tests can be used to run the application (create or update) if not testing using the swagger UI
 
Task APIs Abstract:
	•	Creates a Task, with the Due Date cannot be in the past 
	•	The due date cannot be on a Holiday or weekend. 
	•	The system should not have more than 100 High Priority tasks which have the same due date and are not finished
	•	The domain includes the following: 
	+	Id 
	+	Name 
	+	Description 
	+	Due Date 
	+	Start Date 
	+	End Date 
	+	Priority (High, Medium, Low) 
	+	Status (New, In Progress, Finished) 
 
Design Concepts
	•	Test Driven Development (TDD) 
	•	Dependency Injection (DI) 
	•	Domain Driven Design (DDD) 


	**********
	Additional Clarification before further updates:
	**********
	-can set specific list for Task priorities and task Status options
	-can put the majority of business logic in database stored procedures once
	a database is clarified on which to use
	-clarify how we store DateTime values in our database; what is the
	preferred datatypes: DateTimeb based on MSSQLServer or MySQL
	or other, String
	-How The date will be formatted, possible: yyyy-MM-ddTHH:mm:ss
// example:  2025-03-22
	-can create a regression documentation test scripts to cover All possible
	tangents of use cases for the database, server-side API datatypes
	-need to clarify business compatible databases to store on-premise
	or in a cloud database solution, no problem.

	