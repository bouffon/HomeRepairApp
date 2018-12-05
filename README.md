# HomeRepairApp

"The app addresses homeowner's needs for on-demand home services. 
It allows users to connect with services providers such as plumbers, electricians, locksmiths, among others"

*OCT 25, 2018*: The app is able to create accounts for homeowner's and service providers and supply them to the database, while the admin account
has the ability to view all the accounts in the database. 

*NOV 10, 2018*: The app is able to create services, edit a service's hourly rates and delete services. It is also able to display all of the created services in addition to the hourly rate of each service.

*NOV 20, 2018*: The app now has implentation for Service Providers. Service Providers now have associated company name, company description, licensing toggle, and available hours. Service providers can now add or remove what services they provide.
				"Edit hours" bonus functionality was not implemented, the button will redirect the user into the Service Provider's Service page. 

*Dec 5, 2018*: The app is now fully functional. Homeowners can now search for a service provider by service, hours and rating. They can also book a service provider upon searching and then rate a given service provider.

Authors: Ben Morrison (300025533), Joongho Kim (300043500), Stephen Chen (300033450), Muhammed Izol(300043365), Ethan Lee (300026957)

GitHub Link: https://github.com/bouffon/HomeRepairApp.git

Admin Login:
Username: admin
Password: admin

Default Service Provider Logins:

Username: guy
password: guy

Username: jim
Password: jim

Username: kate
Username: kate

Default Homeowner Login:

Username: bob
Password: bob

Default services:

roofing($25), plumbing($29.75), appliance repair($35), landscaping($21.50), gutting cleaning($23), painting($22.25), mold remediation($33.25), pool maintenance($27.45), extermination($24.99), electrical work($24.99)


There are 17 unit test cases for this app:

*New for deliverable 2*
1. Username validation
2. Email validation
3. Telephone validation
4. FirstName validation
5. Same password validation

*New for deliverable 3*

6. Company name validation
7. Brief company description validation

*New for deliverable 4*

8. Unique search hours
9. Unique booking hours
10. Second search time is greater than first
11. Second booking time is greater than first
12. Check if search time was entered
13. Check if booking time was entered
14.	Search time with 2 digits each can be converted to string
15. Search time with 1 digit each can be converted to string 
16. Can retrieve comment
17. Check if convert time to int method works

Main Testing Device: One Plus 5T (OnePlus ONEPLUSA5010), Android 8.1.0, API 27

These test cases can be found in HomeRepairApp\app\src\androidTest\java\com\example\benji\homerepairapp

The code is found in GitHub.

Build Status
[![Build Status](https://circleci.com/gh/bouffon/HomeRepairApp.png?branch=master)](https://circleci.com/gh/bouffon/HomeRepairApp)