WFC195PA   README.txt

TITLE:
C195 -- Software II Performance Assessment: Medical Clientele Application with Database

PURPOSE:
An application used to create and update customers and appointments for a hypothetical medical firm spanning multiple countries.
To demonstrate proficiency in Java, JavaFX, databases, and SQL.

AUTHOR:
Wesley Fritz

CONTACT INFORMATION:
SID: 1375213
STUDENT EMAIL: wfritz5@wgu.edu
PERSONAL EMAIL: fritzwt@outlook.com
PHONE: (360) 929-2681

STUDENT APPLICATION VERSION:
3

DATE:
January 31, 2023

IDE:
IntelliJ IDEA Community Edition 2022.2.3

FULL JDK OF VERSION USED:
JDK 19

JAVAFX VERSION COMPATIBLE WITH JDK VERSION:
JafaFX-SDK-19

DATABASE DRIVER VERSION:
MYSQL Workbench 8.0.31

DIRECTIONS ON HOW TO RUN PROGRAM:
(Please run using IntelliJ.)

The Login Page:
Users can log in using either the username "test" with password "test", or with the username "admin" with password "admin".
Users are alerted if they have an appointment within 15 minutes of them logging in or not.
Upon logging in, users are taken to the directory page.

The Directory:
The user can select from the Customer page, the Appointments page, or the Reports page to update, delete, or create customers/appointments, or view reports.
The exit button closes the application. Within each page accessible is a back button that will return the user to the directory.

The Customers Page:
The user can create or update customer records on this page. There is a table view where all customer records are shown, on the right.
------To create a customer--
-Select the "Add" radio button.
-Input customer information.
-Select their Country of residence.
-Select their State/Province.
-Click Save. The new customer will be displayed in the table to the right.

------To modify a customer--
-Select a customer first from the table to the right, by clicking on their name.
-Click the "Modify" radio button. The selected customer's information will populate the fields.
-Input customer information.
-If changing State/Province or Country, select Country first, then State/Province.
-Click Save. The updated customer will be displayed in the table to the right. IF SAVE IS NOT CLICKED, INFORMATION WILL NOT BE UPDATED!


------To delete a customer--
-Select a customer first from the table to the right, by clicking on their name.
-Click the "Modify" radio button. The selected customer's information will populate the fields.
-Click Delete. There will be an alert button confirming your decision to delete the selected customer.
-If a customer has appointments already existing, you will be prompted to choose to delete all appointments.
upon choosing yes, all customer's appointments and the customer in question will be deleted.
-Click Yes. The customer will be deleted and this will be reflected in the table to the right.

There is also a button that will return the user to the directory.


The Appointments Page:
The user can create or update appointments on this page. There is a table view where all appointments are shown, on the right.
------To create an appointment--
-Select the "Add" radio button.
-Input appointment information.
-Select an appointment time. If there are overlaps of times, the user will be notified.
-For User_ID, select 1 if logged in as "test", or 2 if logged in as "admin".
-Click Save. The new appointment will be displayed in the table to the right.
*********SCHEDULING TIMES GO OFF OF YOUR COMPUTER'S TIME (IF YOU'RE ON PACIFIC STANDARD TIME,
YOU CAN SCHEDULE 5AM - 7PM YOUR TIME, BECAUSE THE BUSINESS IS ON THE EAST COAST)

------To modify an appointment--
-Select an appointment first from the table to the right, by clicking on it.
-Click the "Modify" radio button. The selected appointment's information will populate the fields.
-Input appointment information.
-For User_ID, select 1 if logged in as "test", or 2 if logged in as "admin".
-Click Save. The updated appointment will be displayed in the table to the right.


------To delete an appointment--
-Select an appointment first from the table to the right, by clicking on it.
-Click the "Modify" radio button. The selected appointment's information will populate the fields.
-Click Delete. There will be an alert button confirming your decision to delete the selected appointment.
-Click Yes. The appointment will be deleted and this will be reflected in the table to the right.

There is also a button that will return the user to the directory.

The Reports Page:
From this page the user can access three different reports regarding information received from the database.
The first report on the left displays how many appointments are in each month, and then how many appointments fall under specific types.
The second report dominates most of the screen, displaying the schedules of the three contacts.
The third report is runs along the bottom, displaying how many customers reside in each country.
There is also a button that will return the user to the directory.


DESCRIPTION OF ADDITIONAL REPORT OF CHOICE RAN IN PART A3F:
The additional report I created in my application focuses on pulling how many customers reside in each country.
While this is a simple report, it can be helpful in business analytics.
By showing where the customer base lies, decisions can be made to open new branches or hire new contacts based upon customer demand.
In addition, pairing the results with visualization software (such as Tableau) can make graphics to better show off the data collected.


MYSQL CONNECTOR DRIVER VERSION NUMBER:
mysql-connector-java-8.0.31




