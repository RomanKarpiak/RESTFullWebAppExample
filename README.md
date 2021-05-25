RESTfulWesAppExample

===============
Pre Requesting
===============

1.JDK 1.8

2.IDE (Intellij idea)

3.Maven

4.Jetty 9.4.14.v20181114

5.Hibernate 5.2.18.Final

6.Spring 5.2.18.Final 

5.PostgreSQL 42.2.19 (on-diskdatabase ) prod profile

6.H2 1.4.200 (in-memorydatabase ) local profile

7.Jackson 2.11.0

8.Jackson mapper 1.9.13

9.REST 

10.Junit4

11.Mockito 3.6.28



===================
How project works.
===================
Step 1: open project form any Spring pluging enable Ide 
Step 2: Set Up postgresql and H2, create database shop for PostgreSQL
Step 3: install Jetty
Step 4: run test mvn test
Step 5: select the profile (prod or local) that you want to start the app with, then run JettyRunner, the database is populated from data.sql.

================
Rest service
================

Api url: http://localhost:8080/customers

Http Metohd :GET

List of customers 

API = http://localhost:8080/customers/get-customer/{id}

Http Metohd :GET

return customer 

API = http://localhost:8080/customers/create-customer

Http Metohd :POST

Create new Customer

API = http://localhost:8080/customers/update-customer/{id}

Http Metohd :PUT

Update customer 

API = http://localhost:8080/customers/delete-customer/{id}

Http Metohd :DELETE

delete customer





