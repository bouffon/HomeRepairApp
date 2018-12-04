# HomeRepairApp

"The app addresses homeowner's needs for on-demand home services. 
It allows users to connect with services providers such as plumbers, electricians, locksmiths, among others"

*OCT 25, 2018*: The app is able to create accounts for homeowner's and service providers and supply them to the database, while the admin account
has the ability to view all the accounts in the database. 

*NOV 10, 2018*: The app is able to create services, edit a service's hourly rates and delete services. It is also able to display all of the created services in addition to the hourly rate of each service.

*NOV 20, 2018*: The app now has implentation for Service Providers. Service Providers now have associated company name, company description, licensing toggle, and available hours. Service providers can now add or remove what services they provide.
				"Edit hours" bonus functionality was not implemented, the button will redirect the user into the Service Provider's Service page. 

Authors: Ben Morrison (300025533), Joongho Kim (300043500), Stephen Chen (300033450), Muhammed Izol(300043365), Ethan Lee (300026957)

GitHub Link: https://github.com/bouffon/HomeRepairApp.git

Admin Login:
Username: admin
Password: admin

Default Service Provider Login:
Username: guy
password: guy

There are 7 unit test cases for the app:

*New for deliverable 2*
1. Username validation
2. Email validation
3. Telephone validation
4. FirstName validation
5. Same password validation

*New for deliverable 3*

6. Company name validation
7. Brief company description validation

Main Testing Device: One Plus 5T (OnePlus ONEPLUSA5010), Android 8.1.0, API 27

These test cases can be found in HomeRepairApp\app\src\androidTest\java\com\example\benji\homerepairapp

The code is found in GitHub.

Build Status
[![Build Status](https://circleci.com/gh/bouffon/HomeRepairApp.png?branch=master)](https://circleci.com/gh/bouffon/HomeRepairApp)