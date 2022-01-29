# Hotel-Reservation-Application
# Introduction

Designing and implementing a Java hotel reservation application which allows customers to find and book a hotel room based on room availability.

1. Designed singleton pattern to avoid Concurrent Exception.
2. Used regex to validate customer's email
3. Used junit to test the program

The main menu and the administrator menu are shown below.

![image](https://user-images.githubusercontent.com/90006503/151637315-45cd6526-34ab-4408-b229-d4552d269d62.png)

## Main Components of the App

The major components of the Hotel Reservation Application will consist of the following:

1. **CLI for the User Interface.** We'll use the *Command Line Interface* (or *CLI* for the user interface. For this, we'll need to have Java monitor the  CLI for user input, so the user can enter commands to search for  available rooms, book rooms, and so on.
2. **Java code.** The second main component is the Java code itself—this is where we add our business logic for the app.
3. **Java collections.** Finally,  we'll use *Java collections* for in-memory storage of the data we need for the app, such as the users' names, room availability, and so on.

![structure](https://user-images.githubusercontent.com/90006503/151637793-58536a78-c7fc-4e67-bc0b-98ed3fc29032.png)


## Application Architecture

Let's talk about the structure or architecture of the application. The app will be separated into the following layers:

1. **User interface (UI)**, including a *main menu* for the users who want to book a room, and an *admin menu* for administrative functions.
2. **Resources** will act as our Application Programming Interface (API) to our UI.
3. **Services** will communicate with our resources, and each other, to build the business logic necessary to provide feedback to our UI.
4. **Data models** will be used to represent the domain that we're using within the system (e.g., rooms, reservations, and customers).


![architecture](https://user-images.githubusercontent.com/90006503/151637814-449abfb7-ab01-4862-a61b-5a90eaa69044.png)


# Tech Stack

OOP+OOD+singleton pattern+maven+junit
